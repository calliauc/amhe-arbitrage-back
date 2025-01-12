insert into CLUB (id, nomCourt, nomComplet, Ville) values(1, 'BEC', 'BEC Escrime', 'Bordeaux');
insert into CLUB (id, nomCourt, nomComplet, Ville) values(2, 'Mesnie', 'La Mesnie du Blanc Castel', 'Blanquefort');
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(1, 'Calliau', 'Clement', 'Makhai', 1);
insert into COMBATTANT(id, nom, prenom, pseudo, club_id) values(2, 'Damien', 'Le Bras', 'Hache', 2);
INSERT INTO match(scorebleu, scorerouge, timersens, bleu_id, rouge_id, id, timer, timermax)
        VALUES (0, 0, true, 1, 2, 1, 0, 100);
INSERT INTO COUP(id, match_id, attaquant_id, defenseur_id) VALUES(1, 1, 1, 2);
INSERT INTO COUP(id, match_id, attaquant_id, defenseur_id) VALUES(2, 1, 1, 2);
INSERT INTO COUP(id, match_id, attaquant_id, defenseur_id) VALUES(3, 1, 1, 2);
alter sequence CLUB_SEQ restart with 3;
alter sequence COMBATTANT_SEQ restart with 3;
alter sequence MATCH_SEQ restart with 2;
alter sequence COUP_SEQ restart with 4;
