Dojo-Clojure
============

## src/code_number_to_letter.clj

It gets a string containing numbers and convert to text, simulating the behaviour and results from a telephone keypad.

To include a pause just send any unmapped char (e.g. "#") between numbers.

0 = " "
2 = ABC  
3 = DEF  
4 = GHI  
5 = JKL  
6 = MNO  
7 = PQRS  
8 = TUV  
9 = WXYZ  


E.g.:

dojo.code-to-letter> (convert-to-text "7773366286660222#25554442777444")
; renato caliari
;= nil