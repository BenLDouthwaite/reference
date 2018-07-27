import nltk

from nltk.corpus import gutenberg

emma = gutenberg.words('austen-emma.txt');
print(len(emma));
emmaText = nltk.Text(emma);
print(emmaText.concordance("then"));

print("\nDisplay average word length, average sentence length, lexical diversity score, file name");
for fileid in gutenberg.fileids():
	num_chars = len(gutenberg.raw(fileid))
	num_words = len(gutenberg.words(fileid))
	num_sents = len(gutenberg.sents(fileid))
	num_vocab = len(set(w.lower() for w in gutenberg.words(fileid)))
	print(round(num_chars/num_words), round(num_words/num_sents), round(num_words/num_vocab), fileid)

emmaRaw = gutenberg.raw('austen-emma.txt');

print("\nPrinting a select chapter from a book");
charter2 = emmaRaw[emmaRaw.index('CHAPTER II'):emmaRaw.index('CHAPTER III')]
print(charter2);

# Brown Corpus

from nltk.corpus import brown

print(brown.categories())

print("\nComparing news genre usage of modal verbs")
news_text = brown.words(categories='news')
fdist = nltk.FreqDist(w.lower() for w in news_text)
modals = ['can', 'could', 'may', 'might', 'must', 'will']
for m in modals:
	print(m + ':', fdist[m], end= ' ')

print("\nComparing genres usage of modal verbs conditional frequency distributions\n")
cfd = nltk.ConditionalFreqDist(
	(genre, word)
	for genre in brown.categories()
	for word in brown.words(categories=genre))
genres = ['news', 'religion', 'hobbies', 'science_fiction', 'romance', 'humor']

modals = ['can', 'could', 'may', 'might', 'must', 'will']
cfd.tabulate(conditions=genres, samples=modals)

whWords = ['who', 'what', 'where', 'when', 'why']
cfd.tabulate(conditions=genres, samples=whWords)

## Reuters Corpus
#Can search by news category or file ID

from nltk.corpus import reuters

print(reuters.fileids())
print(reuters.categories())

tf1 = "training/9865"
tf2 = "test/14826"
files = [tf1, tf2]
print(reuters.categories(tf1))
print(reuters.categories(files))
print(reuters.fileids('barley'))
print(reuters.fileids(['barley', 'corn']))

print(reuters.words(tf1))
print(reuters.raw(tf1))

# Inaugiral Corpus

from nltk.corpus import inaugural

print(inaugural.fileids())
print([fileid[:4] for fileid in inaugural.fileids()])

# TODO Check why this shows empty graph
cfd = nltk.ConditionalFreqDist(
	(target, fileid[:4])
	for fileid in inaugural.fileids()
	for w in inaugural.words(fileid)
	for target in ['america', 'citizen']
	if w.lower().startswith(target)) [1]
cfd.plot()

## Corpora in other languages . UDHR 

# See nltk_data/corpora/udh for more
from nltk.corpus import udhr

print("\nComparison on language word count for the udhr")
languages = ['Chickasaw', 'English', 'German_Deutsch', 'Greenlandic_Inuktikut', 'Hungarian_Magyar', 'Ibibio_Efik']

cfd = nltk.ConditionalFreqDist(
	(lang, len(word))
	for lang in languages
	for word in udhr.words(lang + '-Latin1'))
cfd.plot(cumulative=True)


for fileid in udhr.fileids():
	if 'kor' in fileid.lower():
		print(fileid);


print(set(udhr.words('Korean_Hankuko-UTF8')))
print(udhr.raw('Korean_Hankuko-UTF8'))

kr = nltk.Text(udhr.raw('Korean_Hankuko-UTF8'));
print(kr.concordance('수'));

# TODO nltk.FreqDist(...) appears to be incompatible with UTF8, research mroe
raw = udhr.raw('Zulu-latin1')
fdist = nltk.FreqDist(raw)
fdist.plot()

