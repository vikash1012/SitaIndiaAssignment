CREATE TABLE IF NOT EXISTS public.department
(
    id integer NOT NULL DEFAULT nextval('department_id_seq'::regclass),
    department character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT department_pk PRIMARY KEY (id)
)
