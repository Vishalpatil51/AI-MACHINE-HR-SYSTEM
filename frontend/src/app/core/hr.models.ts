export type EmployeeRole = 'DEVELOPER' | 'HR' | 'PROJECT_MANAGER' | 'ADMIN';

export interface ProjectSummary {
  id: string;
  name: string;
  repo?: string | null;
}

export interface EmployeeSummary {
  id: string;
  name: string;
  email: string;
  role: EmployeeRole;
  position?: string | null;
  team?: string | null;
}

export interface Employee extends EmployeeSummary {
  birthDate?: string | null;
  gender?: string | null;
  recruitmentDate?: string | null;
  score?: number | null;
  salary?: number | null;
  projects?: ProjectSummary[];
}

export interface Project extends ProjectSummary {
  employees?: EmployeeSummary[];
}

export interface Assignment {
  id: string;
  employee: Employee;
  project: Project;
}

export interface EmployeeFormValue {
  name: string;
  email: string;
  birthDate: string;
  gender: string;
  role: EmployeeRole;
  position: string;
  recruitmentDate: string;
  score: number | null;
  salary: number | null;
  team: string;
}

export interface ProjectFormValue {
  name: string;
  repo: string;
}
