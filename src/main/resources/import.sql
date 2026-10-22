-- CLUBS
insert into CLUB (id, nomCourt, nomComplet, Ville) values(1, 'BEC', 'BEC Escrime', 'Bordeaux');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(2, 'Lames du foyer', 'Les lames du foyer', 'Poitiers');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(3, 'Chapitre', 'le Chapitre des Armes', 'Paris');
alter sequence CLUB_SEQ restart with 4;

-- INDIVIDUS
insert into INDIVIDU(id, nom, prenom, pseudo, club_id) values(1, 'Calliau', 'Alix', 'Makhai', 1);
insert into INDIVIDU(id, nom, prenom, pseudo, club_id) values(2, 'Goches', 'Alex', 'Walter', 1);
insert into INDIVIDU(id, nom, prenom, pseudo, club_id) values(3, 'Biron', 'Hyppolyte', null, 2);
insert into INDIVIDU(id, nom, prenom, pseudo, club_id) values(4, 'Pommellet', 'Adrien', null, 3);
alter sequence INDIVIDU_SEQ restart with 5;


-- ITEMS REFERENCE
-- VULNERANTS
INSERT INTO ITEM(id, code, type, libelle) VALUES('1', 'estoc', 'vulnerant', ' porte un estoc');
INSERT INTO ITEM(id, code, type, libelle) VALUES('2', 'taille', 'vulnerant', ' porte un coup de taille');
INSERT INTO ITEM(id, code, type, libelle) VALUES('3', 'entaille', 'vulnerant', ' inflige une entaille');
INSERT INTO ITEM(id, code, type, libelle) VALUES('4', 'lutte', 'vulnerant', ' remporte la lutte contre ');

-- CIBLES
INSERT INTO ITEM(id, code, type, libelle) VALUES('5', 'tête', 'cible', ' à la tête de ');
INSERT INTO ITEM(id, code, type, libelle) VALUES('6', 'torse', 'cible', ' au torse de ');
INSERT INTO ITEM(id, code, type, libelle) VALUES('7', 'bras', 'cible', ' au bras de ');
INSERT INTO ITEM(id, code, type, libelle) VALUES('8', 'main', 'cible', ' à la main de ');
INSERT INTO ITEM(id, code, type, libelle) VALUES('9', 'jambe', 'cible', ' à la jambe de ');
INSERT INTO ITEM(id, code, type, libelle) VALUES('10', 'lutte', 'cible', ' ');

-- TAGS
INSERT INTO ITEM(id, code, type) VALUES('11', 'Poule A', 'tag');
INSERT INTO ITEM(id, code, type) VALUES('12', 'Poule B', 'tag');
INSERT INTO ITEM(id, code, type) VALUES('13', 'Tiers A', 'tag');
INSERT INTO ITEM(id, code, type) VALUES('14', 'Tiers B', 'tag');
INSERT INTO ITEM(id, code, type) VALUES('15', 'Féminin', 'tag');
INSERT INTO ITEM(id, code, type) VALUES('16', 'Finale', 'tag');

alter sequence ITEM_SEQ restart with 17;

-- RULESETS
INSERT INTO ruleset(id, nom, description, timerlimite, timerreverse, vulnerants, cibles)
VALUES (1, 'Longsword', 'Longsword test', '600', true, '{1, 2, 3 }', '{5, 6, 7, 8, 9}');
alter sequence RULESET_SEQ restart with 2;

-- EVENT
INSERT INTO evenement(id, nom, description, dateDebut, dateFin, ruleset_id)
VALUES (0, 'Default', 'Pour les orphelins', null, null, null);
INSERT INTO evenement(id, nom, description, dateDebut, dateFin, ruleset_id)
VALUES (1, 'Burdigala CUP 2', 'Description', '2026-11-28', '2026-11-29', 1);
alter sequence EVENEMENT_SEQ restart with 2;

-- INSCRITS
INSERT INTO inscrit(id, evenement_id, individu_id)
VALUES (1, 1, 1), (2, 1, 2), (3, 1, 3), (4, 1, 4);
alter sequence INSCRIT_SEQ restart with 5;

-- POULE
INSERT INTO poule(id, nom, description, evenement_id)
VALUES (1, 'Poule A', 'Description', 1), (2, 'Poule B', 'Description', 1);
alter sequence POULE_SEQ restart with 3;

-- POULE_INSCRIT
INSERT INTO poule_inscrit(poule_id, inscrits_id)
VALUES (1, 1), (1, 2), (2, 3), (2, 4);
