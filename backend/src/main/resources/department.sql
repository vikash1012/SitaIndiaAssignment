CREATE TABLE IF NOT EXISTS public.department
(
    id integer NOT NULL DEFAULT nextval('department_id_seq'::regclass),
    emp_id integer NOT NULL,
    department character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT department_pkey PRIMARY KEY (id)
)