def duplicate_count(text):
    frequency_dict = {}
    for lowercase_char in text.lower():
        frequency_dict[lowercase_char] = frequency_dict.get(lowercase_char, 0) + 1
    filtered_dict = {k: v for k, v in frequency_dict.items() if v > 1}
    return len(filtered_dict.keys())