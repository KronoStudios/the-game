# The android version
Hem escollit la versio 6 de android, degut a que dona una compatibilitat de 94%, sent la versió més actual una compatibilitat d'usuaris de més del 90%

#  The Java version
La versió de java que hem escollit es la 8, degut a que es la més utilitzada (42% dels developers desenvolupen en java 8).

# Model de Clases
![alt text](https://github.com/KronoStudios/the-game/blob/main/documentation/class_model.jpeg?raw=true)



# Sprint 6
Login de usuari + Registre

S'han convertit les cartes, que abans eren un sol Button, per un Layout amb 3 camps (nom, desc, imatge) que es carrega dinamicament en funció de la carta.

Quan s'acaba una partida, s'insereix el resultat del game a base de dades.

S'ha implementat la pantalla Histoal de partides ( MainMenu -> Profile ) on es veu el historial de partides dels usuaris.
Nota important: Ara mateix filtrem els matches d'un usuari hardcodejat (p.e. id = 11, gcaballe@gmail.com pw: 1234), ja que tenim un dubte per obtenir aquest id al fer la petició de token.


