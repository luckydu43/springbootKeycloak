--INITIALISATION TABLE DROIT
INSERT INTO droit(droit_id,droit_nom) VALUES (1,'DROIT_ADMIN');
INSERT INTO droit(droit_id,droit_nom) VALUES (2,'DROIT_USER');

--INITIALISATION TABLE UTILISATEURS
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (2, 'user2', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (3, 'user3', 0);--inactif user
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (4, 'admin4', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (5, 'user5', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (6, 'user6', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (7, 'user7', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (8, 'user8', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (9, 'user9', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (10, 'user10', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (11, 'user11', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (12, 'user12', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (13, 'user13', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (14, 'user14', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (15, 'user15', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (16, 'user16', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (17, 'user17', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (18, 'user18', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (19, 'user19', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (20, 'user20', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (21, 'user21', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (22, 'user22', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (23, 'user23', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (24, 'user24', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (25, 'user25', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (26, 'user26', 0);--inactif userINSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (1, 'admin', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (27, 'user27', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (28, 'user28', 0);--inactif user
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (29, 'admin29', 1);
INSERT INTO utilisateur(user_id, user_login, user_etat_activite) values (30, 'user30', 1);

-- TABLE DE JOINTURE DES ADMINS
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (1,1);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (4,1);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (29,1);

-- TABLE DE JOINTURE DES USERS
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (1,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (2,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (3,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (4,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (5,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (6,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (7,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (8,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (9,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (10,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (11,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (12,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (13,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (14,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (15,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (16,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (17,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (18,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (19,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (20,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (21,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (22,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (23,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (24,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (25,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (26,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (27,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (28,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (29,2);
INSERT INTO droits_par_utilisateur(user_id,droit_id) VALUES (30,2);

COMMIT;