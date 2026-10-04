-- CLUBS
insert into CLUB (id, nomCourt, nomComplet, Ville) values(1, 'BEC', 'BEC Escrime', 'Bordeaux');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(2, 'Lames du foyer', 'Les lames du foyer', 'Poitiers');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(3, 'Chapitre', 'le Chapitre des Armes', 'Paris');
alter sequence CLUB_SEQ restart with 4;

-- COMBATTANTS
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(1, 'Calliau', 'Alix', 'Makhai', 1);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(2, 'Goches', 'Alex', 'Walter', 1);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(3, 'Biron', 'Hyppolyte', null, 2);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(4, 'Pommellet', 'Adrien', null, 3);
alter sequence COMBATTANT_SEQ restart with 5;

-- TAGS
insert into TAG (id, code) values(1, 'Tier A');
insert into TAG (id, code) values(2, 'Tier B');
insert into TAG (id, code) values(3, 'Féminin');
insert into TAG (id, code) values(4, 'Petite finale');
insert into TAG (id, code) values(5, 'Finale');
alter sequence TAG_SEQ restart with 6;

-- VULNERANTS
INSERT INTO VULNERANT(code, libelle) VALUES('estoc', ' porte un estoc');
INSERT INTO VULNERANT(code, libelle) VALUES('taille', ' porte un coup de taille');
INSERT INTO VULNERANT(code, libelle) VALUES('entaille', ' inflige une entaille');
INSERT INTO VULNERANT(code, libelle) VALUES('lutte', ' remporte la lutte contre ');

-- CIBLES
INSERT INTO CIBLE(code, libelle) VALUES('lutte', ' ');
INSERT INTO CIBLE(code, libelle) VALUES('tête', ' à la tête de ');
INSERT INTO CIBLE(code, libelle) VALUES('torse', ' au torse de ');
INSERT INTO CIBLE(code, libelle) VALUES('bras', ' au bras de ');
INSERT INTO CIBLE(code, libelle) VALUES('main', ' à la main de ');
INSERT INTO CIBLE(code, libelle) VALUES('jambe', ' à la jambe de ');

-- RULESETS
INSERT INTO ruleset(id, nom, description, timerlimite, timerreverse, vulnerants, cibles)
VALUES (1, 'Longsword', 'Longsword test', '600', true, '{estoc, taille, entaille, lutte}', '{tête, torse, bras, main, jambe}');
alter sequence RULESET_SEQ restart with 2;

-- MATCHS
--INSERT INTO match(id, id_a, id_b, score_a, score_b, couleur_a, couleur_b, timer)
--        VALUES (1, 1, 2, 0, 0, 'rgb(68, 143, 255)', 'rgb(252, 102, 102)', 0);
--alter sequence MATCH_SEQ restart with 2;

--COUPS
--INSERT INTO COUP(id, match_id, attaquant_id, attaquant_couleur, defenseur_id, defenseur_couleur, vulnerant_code, cible_code)
--    VALUES(1, 1, 1, 'rgb(68, 143, 255)', 2, 'rgb(252, 102, 102)', 'estoc', 'torse');
--alter sequence COUP_SEQ restart with 4;
