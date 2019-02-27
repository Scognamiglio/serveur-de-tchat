#! /usr/bin/env python
import time
import sys

if len(sys.argv) != 3:
        sys.stderr.write("mauvais nombre d'arguments.\n");
        sys.stderr.write("Syntaxe :\n");
        sys.stderr.write("envoi.py login nb_msg\n");
        sys.exit(1)

print "user " + sys.argv[1]
sys.stdout.flush()
time.sleep(3)
nb_msg = int(sys.argv[2])
for i in range(nb_msg):
	print "msgto [ all ] message" + str(i)
print "quit"

