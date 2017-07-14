select * from departments;
select * from locations;
select * from employees;

DELETE FROM employees;
DELETE FROM locations;
DELETE FROM jobs;
DELETE FROM departments;

delete from locations where location_id > 3200;
delete from departments where department_id > 270;
commit;

select* from departments where department_id = 0;

SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.job_id, e.salary, e.commission_pct, e.manager_id, e.department_id, d.department_name, d.location_id
    FROM employees e
    LEFT JOIN departments d
    ON e.department_id = d.department_id
where lower(department_name) LIKE '%str%' OR lower(department_name) LIKE '%Str%';
    
SELECT a.* FROM employees a 
   INNER JOIN departments b
   on a.department_id = b.department_id
   where department_name LIKE '%str%';
   
   
SELECT a.* FROM employees a INNER JOIN departments b on a.department_id=b.department_id where lower(department_name) LIKE '%str%';