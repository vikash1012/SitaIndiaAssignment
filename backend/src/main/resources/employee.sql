CREATE TABLE IF NOT EXISTS public.employee
(
    id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    emp_name character varying COLLATE pg_catalog."default" NOT NULL,
    amount integer NOT NULL,
    currency character varying COLLATE pg_catalog."default" NOT NULL,
    joining_date date NOT NULL,
    exit_date date NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id)
)
