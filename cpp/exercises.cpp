#include <cassert>
#include <sstream>
#include <map>
#include "exercises.h"

void powers(int base, int limit, function<void(int)> consumer) {
    int pow = 1;
    while (pow <= limit) {
        consumer(pow);
        pow *= base;
    }
}

vector<pair<string, int>> sorted_word_counts(list<string> words) { // from ray toals notes
    map<string,int> counts;
    for (string word : words) {
    counts[word]++;
    }
    auto value_descending = [](auto x, auto y) { return y.second < x.second; };
    vector<pair<string, int>> pairs(counts.begin(), counts.end());
    sort(pairs.begin(), pairs.end(), value_descending);
    return pairs;
}