'use strict'
function defer(f, ms) {
    return function() {
        setTimeout(()=>f.apply(this, arguments), ms);
    }
}

function mydefer(f, ms) {
    return function(...args) {
        // let ctx = this;
        // console.log(this);
        setTimeout(function() {
            return f.apply(this, args);
        }, ms)
    }
}

function sayHi(who) {
    console.log('Hello, ' + who);
}

let sayHiDeferred = defer(sayHi, 2000);
//sayHiDeferred("John");
//mydefer(sayHi, 1000)("Tom");

sayHi.apply