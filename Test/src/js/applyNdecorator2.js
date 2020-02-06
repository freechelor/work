'use strict'
function work(a,b) {
    console.log(a+b);
}

work = spy(work);
console.log('wrapped');
work(1,2);
work(4,5);

function spy(func) {
    wrapper.calls = [];
    function wrapper(...args) {
        wrapper.calls.push(args);
        console.log(this);
        console.log("\nsomething going on");
        return func(args);
    }
    return wrapper;
}

// for(let args of work.calls) {
//     console.log('call : ' + args.join());
// }