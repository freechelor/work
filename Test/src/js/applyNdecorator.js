'use strict'
// async function slow(x) {
//     console.log(`Called with ${x}`);
//     await sleep(5000);
//     setTimeout(()=>console.log('print something - ' + x),1000);
//     return x;
// }

// function sleep(ms) {
//     return new Promise(resolve => setTimeout(resolve, ms));
// }

function slow(x) {
    console.log(`Called with ${x}`);
    let res = fibRecursive(43);
    setTimeout(()=>console.log('print something - ' + x),1000);
    return res;
}

function fibSmart(n) {
    if(n<2) return n;
    let a = 1;  // f(0)
    let b = 1;  // f(1)
    let c = 1;  // just assign init value
    let i = 1;
    while(i++<n) {
        c = a+b;
        a = b;
        b = c;
    }
    return c;
}

function fibRecursive(n) {
    if(n<2) return 1;
    return fibRecursive(n-1) + fibRecursive(n-2);
}

let map = new Map();
function fibDP(n) {
    if(n<2) return 1;
    if(map.get(n)==null||map.get(n)==undefined) {
        let res = fibDP(n-1) + fibDP(n-2);
        map.set(n, res);
        return res;
    } else return map.get(n);
}

function cachingDecorator(func) {
    let cache = new Map();

    return function(x) {
        if(cache.has(x)) {
            return cache.get(x);
        }
        let result = func.call(this, x);
        cache.set(x, result);
        return result;
    }
}



let worker = {
    someMethod() {
        return 1;
    },
    slow(x) {
        let y = 'y'
        console.log('Called with' + x + y);
        return x * this.someMethod();
    }
}


//console.log(fibRecursive(43));
//console.log('returned : ' + slow(1));
//console.log('returned : ' + slow(1));
// console.log('returned : ' + slow(2));

let decoslow = cachingDecorator(slow);
console.log(decoslow(1));
console.log('Again\n' + decoslow(1));

// console.log(slow(2));
// console.log('Again' + slow(2));
let name = 'Default';
function sayHi() {
    console.log(this);
    console.log(this.name);
}

function say(phrase='given phrase') {
    console.log(this.name + ' : ' + phrase);
}

let user = { name : 'John'};
let admin = { name : 'Smith'};

//sayHi();
sayHi.call(user);
sayHi.call(admin);

say.call(user, 'is a user')
say.call(admin, 'is an administrator')

// let worker_slow = cachingDecorator(worker.slow);
// console.log(worker_slow(1));
worker.slow = cachingDecorator(worker.slow);
console.log(worker.slow(1));


let arr = [1,2,3];
console.log(arr.join('-'));
console.log([].join.call(arr,'-'));

let sum_worker = {
    someMethod() {
        return 1;
    },
    slow(min, max) {
        console.log('Called with {' + min + ':' + max + '}');
        return min+max;
    }
}

function cachingDecorator_MultiArgs(func, hash) {
    let cache = new Map();

    return function() {
        let key = hash(arguments);
        if(cache.has(key)) {
            return cache.get(key);
        }
        let result = func.call(this, ...arguments);
        cache.set(key, result);
        console.log(cache);
        return result;
    }
}

function hash(args) {
    return [].join.apply(args,['--']);
    //return args[0] + ',' + args[1];
}

sum_worker.slow = cachingDecorator_MultiArgs(sum_worker.slow, hash);

console.log(sum_worker.slow(3,5));
console.log('Again\n' + sum_worker.slow(3,5));