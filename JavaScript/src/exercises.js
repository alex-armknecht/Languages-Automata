import crypto from "crypto";
export function change(amount) {
  if (amount < 0) {
    throw new RangeError("amount cannot be negative");
  }

  let quarters,
    dimes,
    nickels,
    pennies = 0;

  quarters = Math.floor(amount / 25);
  amount -= quarters * 25;
  dimes = Math.floor(amount / 10);
  amount -= dimes * 10;
  nickels = Math.floor(amount / 5);
  amount -= nickels * 5;
  pennies = amount / 1;
  amount -= pennies * 1;

  let change = [quarters, dimes, nickels, pennies];

  return change;
}

export function stretched(input) {
  const characters = Array.from(input);
  let stetchedWord = "";
  let spacesFound = 0;
  for (let i = 0; i < characters.length; i++) {
    if (characters[i] === " ") {
      spacesFound++;
    } else {
      stetchedWord += characters[i].repeat(i - spacesFound + 1);
    }
  }
  return stetchedWord;
}
export function say(word) {
  if (word == undefined) {
    return "";
  } else {
    return (next_word) => {
      if (next_word == undefined) {
        return word;
      } else {
        return say(word + " " + next_word);
      }
    };
  }
}
export function powers(base, limit, callback) {
  let i = 0;
  while (base ** i <= limit) {
    callback(base ** i);
    i++;
  }
}
export function* powersGenerator(base, limit) {
  let i = 0;
  while (base ** i <= limit) {
    yield base ** i;
    i++;
  }
  return;
}
export function makeCryptoFunctions({ forKey, using, withIV }) {
  let e = (encryptPhrase) => {
    let cipher = crypto.createCipheriv(using, forKey, withIV);
    let encrypt =
      cipher.update(encryptPhrase, "utf-8", "hex") + cipher.final("hex");
    return encrypt;
  };
  let d = (decryptPhrase) => {
    let decipher = crypto.createDecipheriv(using, forKey, withIV);
    let decrypted =
      decipher.update(decryptPhrase, "hex", "utf-8") + decipher.final("utf-8");
    return decrypted;
  };
  return [e, d];
}
export function topTenScorers(data) {
  data = Object.entries(data);
  data = data.flatMap((x) => x[1].map((p) => [...p, x[0]]));
  data = data.filter((x) => x[1] >= 15);
  data = data.map((p) => [
    {
      name: p[0],
      ppg: p[2] / p[1],
      team: p[3],
    },
  ]);
  data = data.flatMap((x) => x);
  // Used this website for sorting an array of objects in javascript
  // https://www.javascripttutorial.net/array/javascript-sort-an-array-of-objects/
  data = data.sort((a, b) => b.ppg - a.ppg);
  data = data.slice(0, 10);
  return data;
}
export async function pokemonInfo(pokeName) {
  // Used this website to learn how to use fetch with async and await
  // https://dmitripavlutin.com/javascript-fetch-async-await/
  const response = await fetch(
    `https://pokeapi.co/api/v2/pokemon/${pokeName}/`
  );
  const pokeInfo = await response.json();
  let data = {
    id: pokeInfo["id"],
    name: pokeInfo["name"],
    weight: pokeInfo["weight"],
  };
  return data;
}
export class Quaternion {
  #coef1;
  #coef2;
  #coef3;
  #coef4;
  constructor(num1, num2, num3, num4) {
    this.#coef1 = num1;
    this.#coef2 = num2;
    this.#coef3 = num3;
    this.#coef4 = num4;
  }
  coefficients() {
    return [this.#coef1, this.#coef2, this.#coef3, this.#coef4];
  }
  plus(otherQuar) {
    return new Quaternion(
      this.#coef1 + otherQuar.#coef1,
      this.#coef2 + otherQuar.#coef2,
      this.#coef3 + otherQuar.#coef3,
      this.#coef4 + otherQuar.#coef4
    );
  }
  times(otherQuar) {
    return new Quaternion(
      this.#coef1 * otherQuar.#coef1 -
        this.#coef2 * otherQuar.#coef2 -
        this.#coef3 * otherQuar.#coef3 -
        this.#coef4 * otherQuar.#coef4,
      this.#coef1 * otherQuar.#coef2 +
        this.#coef2 * otherQuar.#coef1 +
        this.#coef3 * otherQuar.#coef4 -
        this.#coef4 * otherQuar.#coef3,
      this.#coef1 * otherQuar.#coef3 -
        this.#coef2 * otherQuar.#coef4 +
        this.#coef3 * otherQuar.#coef1 +
        this.#coef4 * otherQuar.#coef2,
      this.#coef1 * otherQuar.#coef4 +
        this.#coef2 * otherQuar.#coef3 -
        this.#coef3 * otherQuar.#coef2 +
        this.#coef4 * otherQuar.#coef1
    );
  }
}
