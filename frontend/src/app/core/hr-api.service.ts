import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Assignment, Employee, EmployeeFormValue, Project, ProjectFormValue } from './hr.models';

@Injectable({ providedIn: 'root' })
export class HrApiService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = environment.apiBaseUrl.replace(/\/$/, '');

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.url('/employees/getAllEmployees'));
  }

  createEmployee(employee: EmployeeFormValue): Observable<Employee> {
    return this.http.post<Employee>(this.url('/employees/saveEmployee'), this.cleanPayload(employee));
  }

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url('/projects/getAllProjects'));
  }

  createProject(project: ProjectFormValue): Observable<Project> {
    return this.http.post<Project>(this.url('/projects/saveProject'), this.cleanPayload(project));
  }

  assign(employeeId: string, projectId: string): Observable<Assignment> {
    return this.http.post<Assignment>(this.url(`/assignments/${employeeId}/${projectId}`), {});
  }

  unassign(employeeId: string, projectId: string): Observable<void> {
    return this.http.delete<void>(this.url(`/assignments/${employeeId}/${projectId}`));
  }

  private url(path: string): string {
    return `${this.apiBaseUrl}${path}`;
  }

  private cleanPayload<T extends object>(value: T): Partial<T> {
    return Object.fromEntries(
      Object.entries(value).filter(([, field]) => field !== '' && field !== null && field !== undefined)
    ) as Partial<T>;
  }
}
