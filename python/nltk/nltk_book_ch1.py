import nltk

from nltk import FreqDist
from nltk.book import text1


print("\nSearch for all occurances of the parameter in the text with context:");
text1.concordance("want")

print("\nSearch for other words that appear in similar contexts to the parameter")
text1.similar("monstrous")

text1.common_contexts(["test", "try"])

#Is blocking to the script continuing
text1.dispersion_plot(["mean", "know"]) 

len(text1)
set(text1)
sorted(set(text1))
len(set(text1))

print("\nFind all words in a text meetine a predicate (word length)")
V = set(text1)
long_words = [w for w in V if len(w) > 15]
print(sorted(long_words))

print("\nFind words to categorise a text")
fdist1 = FreqDist(text1)
print(sorted(w for w in set(text1) if len(w) > 9 and fdist1[w] > 8))