CREATE TABLE IF NOT EXISTS public.employee
(
    id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    amount integer NOT NULL,
    currency character varying(255) COLLATE pg_catalog."default",
    emp_name character varying(255) COLLATE pg_catalog."default",
    exit_date date,
    joining_date date,
    dep_id_fk integer NOT NULL DEFAULT nextval('employee_dep_id_fk_seq'::regclass)
)