from cryptography.fernet import Fernet
import re
from dataclasses import dataclass
from typing import Tuple
import math

def change (amount: int) -> Tuple[int, int, int, int]:
    quarters = dimes = nickels = pennies = 0
    if not type (amount) is int:
        raise TypeError("Only integers are allowed")
    if amount < 0:
        raise ValueError("amount cannot be negative")

    quarters = math.floor (amount / 25)
    amount -= quarters * 25
    dimes = math.floor (amount / 10)
    amount -= dimes * 10
    nickels = math.floor (amount / 5)
    amount -= nickels * 5
    pennies = amount

    return (quarters, dimes, nickels, pennies)


def stretched(s: str, /) -> str:
    s: str = re.sub(r'\s+', '', s)
    # Got that line of code from https://bobbyhadz.com/blog/python-remove-spaces-tabs-newlines-from-string
    return_str: str = ""
    for index, letter in enumerate(s):
        return_str += (letter * (index + 1))
    return return_str


def powers(*, base: int, limit: int) -> list:
    i: int = 0
    while base ** i <= limit:
        yield base ** i
        i += 1
    return []


def say(word=None, /):
    if word is None:
        return ''
    return lambda next_word = None: word if next_word is None else say(word + " " + next_word)


def find_first_then_lower(predicate, words):
    if not words:
        raise ValueError("Empty list")
    for word in words:
        if predicate(word):
            return word.lower()
    raise ValueError("Nothing in list satisfies given property")


# Sorting function inspired by: https://www.programiz.com/python-programming/methods/list/sort
def comparator(list_item: float):
    list_item1: float = list_item[3]
    list_item2: float = list_item[2]
    return list_item1/list_item2


def top_ten_scorers(data: dict) -> list:
    result_list: list = []
    top_ten_list: list = []
    formatted_list: list = []

    for key in data:
        big_list: list = data[key]
        for small_list in big_list:
            small_list.insert(0, key)
            if small_list[2] >= 15:
                result_list.append(small_list)
                result_list.sort(reverse=True, key=comparator)
    top_ten_list = result_list[0:10]

    for small_list in top_ten_list:
        # To format to two decimal places: https://tutorial.eyehunts.com/python/how-to-display-2-decimal-places-in-python-example-code/#:~:text=Use%20str.,decimal%20places%20in%20the%20console.
        player_name: str = small_list[1]
        points_per_game: float = small_list[3]/small_list[2]
        team_name: str = small_list[0]
        formatted_list.append(
            f'{player_name}|{points_per_game:.2f}|{team_name}')

    return formatted_list


def crypto_functions():
    # info about fernet keys: https://www.geeksforgeeks.org/fernet-symmetric-encryption-using-cryptography-module-in-python/
    key = Fernet.generate_key()
    f = Fernet(key)

    def encode(data):
        return f.encrypt(data)

    def decode(token):
        return f.decrypt(token)

    return encode, decode


@dataclass(frozen=True)
class Quaternion:

    num1: int
    num2: int
    num3: int
    num4: int

    @property
    def coefficients(self):
        return (self.num1, self.num2, self.num3, self.num4)

    def __add__(self, otherQuaternion):
        return Quaternion(
            self.num1 + otherQuaternion.num1,
            self.num2 + otherQuaternion.num2,
            self.num3 + otherQuaternion.num3,
            self.num4 + otherQuaternion.num4)

    def __mul__(self, otherQuaternion):
        return Quaternion(
            self.num1 * otherQuaternion.num1 -
            self.num2 * otherQuaternion.num2 -
            self.num3 * otherQuaternion.num3 -
            self.num4 * otherQuaternion.num4,
            self.num1 * otherQuaternion.num2 +
            self.num2 * otherQuaternion.num1 +
            self.num3 * otherQuaternion.num4 -
            self.num4 * otherQuaternion.num3,
            self.num1 * otherQuaternion.num3 -
            self.num2 * otherQuaternion.num4 +
            self.num3 * otherQuaternion.num1 +
            self.num4 * otherQuaternion.num2,
            self.num1 * otherQuaternion.num4 +
            self.num2 * otherQuaternion.num3 -
            self.num3 * otherQuaternion.num2 +
            self.num4 * otherQuaternion.num1)
