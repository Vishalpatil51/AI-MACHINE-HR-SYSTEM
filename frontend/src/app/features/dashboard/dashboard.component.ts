import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { HrApiService } from '../../core/hr-api.service';
import { Employee, EmployeeFormValue, EmployeeRole, Project, ProjectFormValue } from '../../core/hr.models';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyPipe, DatePipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  private readonly hrApi = inject(HrApiService);

  readonly roles: EmployeeRole[] = ['DEVELOPER', 'HR', 'PROJECT_MANAGER', 'ADMIN'];
  readonly employees = signal<Employee[]>([]);
  readonly projects = signal<Project[]>([]);
  readonly selectedEmployeeId = signal<string>('');
  readonly selectedProjectId = signal<string>('');
  readonly isLoading = signal<boolean>(false);
  readonly isSavingEmployee = signal<boolean>(false);
  readonly isSavingProject = signal<boolean>(false);
  readonly isAssigning = signal<boolean>(false);
  readonly statusMessage = signal<string>('');
  readonly errorMessage = signal<string>('');

  employeeForm: EmployeeFormValue = this.defaultEmployeeForm();
  projectForm: ProjectFormValue = this.defaultProjectForm();

  readonly activeAssignments = computed(() =>
    this.employees().flatMap((employee) =>
      (employee.projects ?? []).map((project) => ({ employee, project }))
    )
  );

  readonly totalPayroll = computed(() =>
    this.employees().reduce((sum, employee) => sum + (employee.salary ?? 0), 0)
  );

  readonly averageScore = computed(() => {
    const scoredEmployees = this.employees().filter((employee) => typeof employee.score === 'number');
    if (scoredEmployees.length === 0) {
      return 0;
    }

    return Math.round(
      scoredEmployees.reduce((sum, employee) => sum + (employee.score ?? 0), 0) / scoredEmployees.length
    );
  });

  ngOnInit(): void {
    this.loadDashboard();
  }

  loadDashboard(): void {
    this.isLoading.set(true);
    this.errorMessage.set('');

    forkJoin({
      employees: this.hrApi.getEmployees(),
      projects: this.hrApi.getProjects()
    }).subscribe({
      next: ({ employees, projects }) => {
        this.employees.set(employees ?? []);
        this.projects.set(projects ?? []);
        this.ensureAssignmentSelections();
        this.statusMessage.set('Dashboard data refreshed.');
        this.isLoading.set(false);
      },
      error: () => {
        this.errorMessage.set('Unable to load HR data. Confirm the Spring Boot API is running on port 8989.');
        this.isLoading.set(false);
      }
    });
  }

  createEmployee(): void {
    if (!this.employeeForm.name || !this.employeeForm.email || !this.employeeForm.role) {
      this.errorMessage.set('Name, email, and role are required for employees.');
      return;
    }

    this.isSavingEmployee.set(true);
    this.clearMessages();

    this.hrApi.createEmployee(this.employeeForm).subscribe({
      next: (employee) => {
        this.employees.update((employees) => [employee, ...employees]);
        this.employeeForm = this.defaultEmployeeForm();
        this.ensureAssignmentSelections();
        this.statusMessage.set('Employee created successfully.');
        this.isSavingEmployee.set(false);
      },
      error: () => {
        this.errorMessage.set('Could not create employee. Check that the email is unique and all fields are valid.');
        this.isSavingEmployee.set(false);
      }
    });
  }

  createProject(): void {
    if (!this.projectForm.name) {
      this.errorMessage.set('Project name is required.');
      return;
    }

    this.isSavingProject.set(true);
    this.clearMessages();

    this.hrApi.createProject(this.projectForm).subscribe({
      next: (project) => {
        this.projects.update((projects) => [project, ...projects]);
        this.projectForm = this.defaultProjectForm();
        this.ensureAssignmentSelections();
        this.statusMessage.set('Project created successfully.');
        this.isSavingProject.set(false);
      },
      error: () => {
        this.errorMessage.set('Could not create project. Check the API response and try again.');
        this.isSavingProject.set(false);
      }
    });
  }

  assignSelected(): void {
    const employeeId = this.selectedEmployeeId();
    const projectId = this.selectedProjectId();

    if (!employeeId || !projectId) {
      this.errorMessage.set('Select both an employee and project to create an assignment.');
      return;
    }

    this.isAssigning.set(true);
    this.clearMessages();

    this.hrApi.assign(employeeId, projectId).subscribe({
      next: () => {
        this.statusMessage.set('Assignment saved successfully.');
        this.isAssigning.set(false);
        this.loadDashboard();
      },
      error: () => {
        this.errorMessage.set('Could not save assignment. Verify both records still exist.');
        this.isAssigning.set(false);
      }
    });
  }

  unassign(employeeId: string, projectId: string): void {
    this.clearMessages();
    this.hrApi.unassign(employeeId, projectId).subscribe({
      next: () => {
        this.statusMessage.set('Assignment removed.');
        this.loadDashboard();
      },
      error: () => {
        this.errorMessage.set('Could not remove assignment. Please retry.');
      }
    });
  }

  updateSelectedEmployee(event: Event): void {
    this.selectedEmployeeId.set((event.target as HTMLSelectElement).value);
  }

  updateSelectedProject(event: Event): void {
    this.selectedProjectId.set((event.target as HTMLSelectElement).value);
  }

  private ensureAssignmentSelections(): void {
    const firstEmployeeId = this.employees()[0]?.id ?? '';
    const firstProjectId = this.projects()[0]?.id ?? '';

    if (!this.employees().some((employee) => employee.id === this.selectedEmployeeId())) {
      this.selectedEmployeeId.set(firstEmployeeId);
    }

    if (!this.projects().some((project) => project.id === this.selectedProjectId())) {
      this.selectedProjectId.set(firstProjectId);
    }
  }

  private clearMessages(): void {
    this.errorMessage.set('');
    this.statusMessage.set('');
  }

  private defaultEmployeeForm(): EmployeeFormValue {
    return {
      name: '',
      email: '',
      birthDate: '',
      gender: '',
      role: 'DEVELOPER',
      position: '',
      recruitmentDate: '',
      score: null,
      salary: null,
      team: ''
    };
  }

  private defaultProjectForm(): ProjectFormValue {
    return {
      name: '',
      repo: ''
    };
  }
}
