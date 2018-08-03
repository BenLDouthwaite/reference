import sys
import codecs

from tika import parser

input_file = sys.argv[1]
output_file = sys.argv[2]

raw = parser.from_file(input_file)
pdfText = raw['content']
pdfTextStripped = " ".join(pdfText.split())

file = codecs.open(output_file, "w", "utf-8")
file.write(pdfTextStripped)
file.close()

