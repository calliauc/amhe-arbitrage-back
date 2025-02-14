-- CLUBS
insert into CLUB (id, nomCourt, nomComplet, Ville) values(1, 'BEC', 'BEC Escrime', 'Bordeaux');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(2, 'Mesnie', 'La Mesnie du Blanc Castel', 'Blanquefort');
alter sequence CLUB_SEQ restart with 3;

-- COMBATTANTS
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(1, 'Calliau', 'Clement', 'Makhai', 1);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(2, 'Goches', 'Alex', 'Walter', 1);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(3, 'Le Bras', 'Damien', 'Hache', 2);
alter sequence COMBATTANT_SEQ restart with 4;

-- TAGS
insert into TAG (id, code) values(1, 'B-CUP-25');
insert into TAG (id, code) values(2, 'Epée');
insert into TAG (id, code) values(3, 'Hache');
insert into TAG (id, code) values(4, 'Poule-01');
insert into TAG (id, code) values(5, 'Poule-02');
insert into TAG (id, code) values(6, 'Poule-03');
insert into TAG (id, code) values(7, 'Poule-04');
insert into TAG (id, code) values(8, 'Poule-05');
insert into TAG (id, code) values(9, 'Poule-06');
insert into TAG (id, code) values(10, 'Poule-11');
insert into TAG (id, code) values(11, 'Poule-12');
insert into TAG (id, code) values(12, 'Poule-13');
insert into TAG (id, code) values(13, 'Poule-14');
insert into TAG (id, code) values(14, 'Poule-15');
insert into TAG (id, code) values(15, 'Poule-16');
insert into TAG (id, code) values(16, 'Finale-p');
insert into TAG (id, code) values(17, 'Finale-g');
alter sequence TAG_SEQ restart with 18;

-- VULNERANTS
INSERT INTO VULNERANT(code, libelle) VALUES('estoc', ' porte un estoc');
INSERT INTO VULNERANT(code, libelle) VALUES('taille', ' porte un coup de taille');
INSERT INTO VULNERANT(code, libelle) VALUES('entaille', ' inflige une entaille');
INSERT INTO VULNERANT(code, libelle) VALUES('lutte', ' remporte la lutte contre ');
INSERT INTO VULNERANT(code, libelle) VALUES('dague', ' porte un coup de dague ');
INSERT INTO VULNERANT(code, libelle) VALUES('croix', ' porte un coup de croix ');
INSERT INTO VULNERANT(code, libelle) VALUES('talon', ' porte un coup du talon ');

-- CIBLES
INSERT INTO CIBLE(code, libelle) VALUES('lutte', ' ');
INSERT INTO CIBLE(code, libelle) VALUES('tête', ' à la tête de ');
INSERT INTO CIBLE(code, libelle) VALUES('torse', ' au torse de ');
INSERT INTO CIBLE(code, libelle) VALUES('bras', ' au bras de ');
INSERT INTO CIBLE(code, libelle) VALUES('main', ' à la main de ');
INSERT INTO CIBLE(code, libelle) VALUES('jambe', ' à la jambe de ');
INSERT INTO CIBLE(code, libelle) VALUES('gorge', ' à la gorge de ');

-- RULESETS
INSERT INTO ruleset(id, nom, description, timerlimite, timerreverse, vulnerants, cibles)
VALUES (1, 'Longsword', 'Longsword test', '600', false, '{estoc, taille, entaille, lutte}', '{tête, torse, bras, main, jambe}');
INSERT INTO ruleset(id, nom, description, timerlimite, timerreverse, vulnerants, cibles)
VALUES (2, 'Hache', 'Hache arme test', '600', false, '{dague, croix, talon, lutte}', '{tête, gorge}');
alter sequence RULESET_SEQ restart with 3;

-- MATCHS
--INSERT INTO match(id, id_a, id_b, score_a, score_b, couleur_a, couleur_b, timer)
--        VALUES (1, 1, 2, 0, 0, 'rgb(68, 143, 255)', 'rgb(252, 102, 102)', 0);
--alter sequence MATCH_SEQ restart with 2;

--COUPS
--INSERT INTO COUP(id, match_id, attaquant_id, attaquant_couleur, defenseur_id, defenseur_couleur, vulnerant_code, cible_code)
--    VALUES(1, 1, 1, 'rgb(68, 143, 255)', 2, 'rgb(252, 102, 102)', 'estoc', 'torse');
--alter sequence COUP_SEQ restart with 4;
