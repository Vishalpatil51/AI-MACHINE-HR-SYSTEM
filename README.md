# AI-MACHINE-HR-SYSTEM

AI Machines HR System is a Spring Boot microservice-based HR Management application designed to manage employees, projects, and employee-project assignments efficiently. The system provides REST APIs for employee management, project management, employee evaluation, and role-based authorization using Spring Security.

## Repository layout

- `EmployeeAssignments/` — Spring Boot API for employees, projects, and assignments.
- `frontend/` — Angular dashboard for the HR assignment workflow.

## Backend quick start

```bash
cd EmployeeAssignments
./mvnw spring-boot:run
```

The API is configured to run on port `8989` and exposes these endpoints used by the frontend:

- `GET /employees/getAllEmployees`
- `POST /employees/saveEmployee`
- `GET /projects/getAllProjects`
- `POST /projects/saveProject`
- `POST /assignments/{employeeId}/{projectId}`
- `DELETE /assignments/{employeeId}/{projectId}`

## Angular frontend quick start

```bash
cd frontend
npm install
npm start
```

The `npm start` script launches Angular with `proxy.conf.json`, forwarding `/employees`, `/projects`, and `/assignments` requests to `http://localhost:8989`. Start the Spring Boot API first, then open the Angular dev-server URL shown in the terminal.

## Frontend capabilities

- View workforce, project, assignment, score, and payroll summary metrics.
- Create employees with role, team, profile, date, score, and salary details.
- Create projects with optional repository links.
- Assign and unassign employees to projects.
- Browse employee, project, and active assignment lists from one responsive dashboard.
