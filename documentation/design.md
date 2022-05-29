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

També hem afegit 3 cartes noves: daño AoE, cures AoE, shield AoE. de moment shield/cura aoe encara nof unciona correctament, però ho hem afegit pel tema "visual" de cards.


# Sprint 7

Entrega final del projecte

S'han fet els moduls Collection (Modificar la teva deck, guardada en sharedpreferences).

Millores visuals (backgrounds, cards/characters, matchHistory)

Afegides més cartes (escalats amb strength)

Bugfixes varis (re-shuffle, aoe shield, aoe heal)

Afegit soroll de victoria i animacions al rebre dany.


S'ha configurat el servidor per a que es pugui accedir remotament (necessita estar obert al PC del Guillem).
El .apk del projecte es pot compartir per drive el dia de la presentació, per que cadascú s'ho executi.
Generarem 2 .apk, un per Android 9 i un per Android 11. 

(S'ha de canviar gradle.build les linies seguents:)

10: minSdk 30
51: implementation("com.squareup.okhttp3:okhttp:4.9.3")
