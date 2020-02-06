function sayHi() {
    console.log('Hi guys');
}

let hi = function() {
    console.log('variable function')
}

console.log(sayHi);
sayHi();

console.log('===');

console.log(hi);
hi();

let anotherhi = sayHi();

console.log(anotherhi)

