SELECT film.title AS "Titel", presentation.date AS "Aufführungsdatum" FROM film
JOIN presentation ON film.film_id = presentation.presentation_id
WHERE presentation.date LIKE '2018-%'
ORDER BY presentation.date, film.title ASC;

SELECT film.* FROM film
LEFT JOIN presentation ON film.film_id = presentation.film_idfs
WHERE presentation.presentation_id IS NULL
ORDER BY film.title;

SELECT film.title, COUNT(presentation.presentation_id) FROM presentation
RIGHT JOIN film ON film.film_id = presentation.film_idfs
GROUP BY film.film_id
ORDER BY COUNT(presentation.presentation_id), film.title;

SELECT film.*, COUNT(member.member_id) AS "Anzahl Mitglieder" FROM film
JOIN presentation ON film.film_id = presentation.film_idfs
JOIN visit ON visit.presentation_idfs = presentation.presentation_id
JOIN member ON member.member_id = visit.member_idfs
GROUP BY film.film_id
ORDER BY COUNT(member.member_id) DESC, film.title ASC;

SELECT member.*, COUNT(presentation.presentation_id) AS "Anzahl besuchte Präsentationen" FROM board_member
JOIN member ON board_member.member_idfs = member.member_id
JOIN visit ON visit.member_idfs = member.member_id
JOIN presentation ON presentation.presentation_id = visit.presentation_idfs
GROUP BY board_member.board_member_id
ORDER BY COUNT(presentation.presentation_id)
LIMIT 1;
