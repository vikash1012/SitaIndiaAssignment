CREATE TABLE IF NOT EXISTS public.employee
(
    id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    amount integer NOT NULL,
    currency character varying(255) COLLATE pg_catalog."default" NOT NULL,
    emp_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    exit_date date NOT NULL,
    joining_date date NOT NULL,
    dep_id_fk integer NOT NULL DEFAULT nextval('employee_dep_id_fk_seq'::regclass),
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT dep_id_fk FOREIGN KEY (dep_id_fk)
        REFERENCES public.department (id)
)
