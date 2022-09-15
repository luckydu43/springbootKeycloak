-- basic.droit definition

-- Drop table

-- DROP TABLE basic.droit;

CREATE TABLE basic.droit (
	droit_id serial4 NOT NULL,
	droit_nom varchar(30) NOT NULL,
	CONSTRAINT droit_droit_nom_key UNIQUE (droit_nom),
	CONSTRAINT droit_pkey PRIMARY KEY (droit_id)
);

-- Permissions

ALTER TABLE basic.droit OWNER TO postgres;
GRANT ALL ON TABLE basic.droit TO postgres;


-- basic.utilisateur definition

-- Drop table

-- DROP TABLE basic.utilisateur;

CREATE TABLE basic.utilisateur (
	user_id serial4 NOT NULL,
	user_login varchar(30) NOT NULL,
	user_etat_activite int4 NULL,
	CONSTRAINT utilisateur_pkey PRIMARY KEY (user_id),
	CONSTRAINT utilisateur_user_login_key UNIQUE (user_login)
);

-- Permissions

ALTER TABLE basic.utilisateur OWNER TO postgres;
GRANT ALL ON TABLE basic.utilisateur TO postgres;


-- basic.droits_par_utilisateur definition

-- Drop table

-- DROP TABLE basic.droits_par_utilisateur;

CREATE TABLE basic.droits_par_utilisateur (
	user_id int4 NOT NULL,
	droit_id int4 NOT NULL,
	CONSTRAINT droits_par_utilisateur_pkey PRIMARY KEY (user_id, droit_id),
	CONSTRAINT fk_droit FOREIGN KEY (droit_id) REFERENCES basic.droit(droit_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT fk_utilisateur FOREIGN KEY (user_id) REFERENCES basic.utilisateur(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- Permissions

ALTER TABLE basic.droits_par_utilisateur OWNER TO springboot;
GRANT ALL ON TABLE basic.droits_par_utilisateur TO springboot;
