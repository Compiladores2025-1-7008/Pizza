;; -*- lexical-binding: t; -*-

(TeX-add-style-hook
 "P1"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("aleph-notas" "compacto")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("aleph-comandos" "") ("enumitem" "") ("parskip" "") ("amssymb" "") ("multicol" "") ("amsmath" "") ("graphicx" "") ("algorithm" "") ("algpseudocode" "noend") ("xcolor" "") ("listings" "")))
   (add-to-list 'LaTeX-verbatim-environments-local "lstlisting")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "lstinline")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "lstinline")
   (TeX-run-style-hooks
    "latex2e"
    "aleph-notas"
    "aleph-notas10"
    "aleph-comandos"
    "enumitem"
    "parskip"
    "amssymb"
    "multicol"
    "amsmath"
    "graphicx"
    "algorithm"
    "algpseudocode"
    "xcolor"
    "listings"))
 :latex)

