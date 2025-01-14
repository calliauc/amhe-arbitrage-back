-- CLUBS
insert into CLUB (id, nomCourt, nomComplet, Ville) values(1, 'BEC', 'BEC Escrime', 'Bordeaux');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(2, 'Mesnie', 'La Mesnie du Blanc Castel', 'Blanquefort');
alter sequence CLUB_SEQ restart with 3;
-- COMBATTANTS
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(1, 'Calliau', 'Clement', 'Makhai', 1);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(2, 'Le Bras', 'Damien', 'Hache', 2);
alter sequence COMBATTANT_SEQ restart with 3;
-- MATCHS
INSERT INTO match(id, bleu_id, rouge_id,scorebleu, scorerouge, timerstart, timerend, timerreverse)
        VALUES (1, 1, 2, 0, 0, 0, 240, false);
alter sequence MATCH_SEQ restart with 2;
-- VULNERANTS
INSERT INTO VULNERANT(id, code, libelle) VALUES(1, 'estoc', ' porte un estoc');
INSERT INTO VULNERANT(id, code, libelle) VALUES(2, 'taille', ' porte un coup de taille');
INSERT INTO VULNERANT(id, code, libelle) VALUES(3, 'entaille', ' inflige une entaille');
INSERT INTO VULNERANT(id, code, libelle) VALUES(4, 'lutte', ' remporte la lutte contre ');
alter sequence VULNERANT_SEQ restart with 5;
-- CIBLES
INSERT INTO CIBLE(id, code, libelle) VALUES(1, 'tête', ' à la tête de ');
INSERT INTO CIBLE(id, code, libelle) VALUES(2, 'torse', ' au torse de ');
INSERT INTO CIBLE(id, code, libelle) VALUES(3, 'bras', ' au bras de ');
alter sequence CIBLE_SEQ restart with 4;

--COUPS
INSERT INTO COUP(id, match_id, attaquant_id, attaquant_couleur, defenseur_id, defenseur_couleur, vulnerant_id, cible_id)
    VALUES(1, 1, 1, 'bleu', 2, 'rouge', 1, 1);
alter sequence COUP_SEQ restart with 4;
