-- ITEMS REFERENCE
-- VULNERANTS
INSERT INTO ITEM(id, type, code, libelle) VALUES
('1', 'vulnerant', 'estoc', ' porte un estoc'),
('2', 'vulnerant', 'taille', ' porte un coup de taille'),
('3', 'vulnerant', 'entaille', ' inflige une entaille'),
('4', 'vulnerant', 'lutte', ' remporte la lutte contre ');

-- CIBLES
INSERT INTO ITEM(id, type, code, libelle) VALUES
('5', 'cible', 'tête', ' à la tête de '),
('6', 'cible', 'torse', ' au torse de '),
('7', 'cible', 'bras', ' au bras de '),
('8', 'cible', 'main', ' à la main de '),
('9', 'cible', 'jambe', ' à la jambe de '),
('10', 'cible', 'lutte', ' ');

-- TAGS
INSERT INTO ITEM(id, type, code) VALUES
('11', 'tag', 'Poule A'),
('12', 'tag', 'Poule B'),
('13', 'tag', 'Tiers A'),
('14', 'tag', 'Tiers B'),
('15', 'tag', 'Féminin'),
('16', 'tag', 'Finale');

-- PENALITES
INSERT INTO ITEM(id, type, code, libelle) VALUES
('17', 'sanction', 'avertissement', 'Attend une confirmation'),
('18', 'sanction', 'carton jaune', 'Perte de un (1) point'),
('19', 'sanction', 'carton rouge', 'Défaite du match en cours'),
('20', 'sanction', 'carton noir', 'Défaite et exclusion');

-- TYPES-ECHANGE
INSERT INTO ITEM(id, type, code, libelle, flags) VALUES
('21', 'type-echange', 'clean', 'Touche seule', '{"toucheClean"}'),
('22', 'type-echange', 'simu', 'Touches simultanées', '{simu}'),
('23', 'type-echange', 'double', 'Double touche', '{"prio"}'),
('24', 'type-echange', 'afterblow', 'Blow/afterblow', '{"prio"}');

alter sequence ITEM_SEQ restart with 25;

-- RULESETS
INSERT INTO ruleset(id, nom, description, timerlimite, timerreverse, typesechange, vulnerants, cibles)
VALUES (1, 'Longsword', 'Longsword test', '600', true, '{21, 22, 23, 24 }', '{1, 2, 3 }', '{5, 6, 7, 8, 9}');
alter sequence RULESET_SEQ restart with 2;

-- CLUBS
insert into CLUB (id, nomCourt, nomComplet, Ville) values
(1, 'BEC', 'BEC Escrime', 'Bordeaux'),
(2, 'Lames du foyer', 'Les lames du foyer', 'Poitiers'),
(3, 'Chapitre', 'le Chapitre des Armes', 'Paris');
alter sequence CLUB_SEQ restart with 4;

-- INDIVIDUS
insert into INDIVIDU(id, nom, prenom, pseudo, club_id) values
(1, 'Calliau', 'Alix', 'Makhai', 1),
(2, 'Goches', 'Alex', 'Walter', 1),
(3, 'Biron', 'Hyppolyte', null, 2),
(4, 'Pommellet', 'Adrien', null, 3),
(5, 'Chiaradia', 'Hugo', null, 1);
alter sequence INDIVIDU_SEQ restart with 6;

-- EVENT
INSERT INTO evenement(id, nom, description, dateDebut, dateFin, nbPlacesMax, ruleset_id) VALUES
(0, 'Default', 'Pour les orphelins', null, null, null, null),
(1, 'Burdigala CUP 2', 'Description', '2026-11-28', '2026-11-29', 40, 1);
alter sequence EVENEMENT_SEQ restart with 2;

-- INSCRITS
INSERT INTO inscrit(id, evenement_id, individu_id, desinscrit, liste_attente) VALUES
(1, 1, 1, false, false),
(2, 1, 2, false, false),
(3, 1, 3, false, false),
(4, 1, 4, false, false),
(5, 1, 5, false, false);
alter sequence INSCRIT_SEQ restart with 6;

-- POULE
INSERT INTO poule(id, nom, prefixe, description, evenement_id) VALUES
(1, 'Poule A', 'A', 'Description', 1),
(2, 'Poule B', 'B', 'Description', 1);

alter sequence POULE_SEQ restart with 3;

-- POULE_INSCRIT
INSERT INTO poule_inscrit(poule_id, inscrits_id) VALUES
(1, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5);

-- MATCH
INSERT INTO match(
id, evenement_id, id_a, id_b, id_ruleset,
score_a, score_b, timer, poule_id, tags,
statut, datecreation, datedebut, datefin
) VALUES (
1,1,1,2,1,
2,2,0,1,NULL,
'nouveau','2026-11-28 15:45:30',NULL,NULL
);
alter sequence MATCH_SEQ restart with 3;

-- POULE_INSCRIT
INSERT INTO poule_match(poule_id, matchs_id) VALUES
(1, 1);
