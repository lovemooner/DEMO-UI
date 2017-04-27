/**
 * @description 公用组件
 * @author lovemooner
 */
var GLOBAL = {};

// Lang 相关
GLOBAL.Lang = {};

/**
 * @description 滤除字符串两边的空格
 * @param {String} ostr
 * @returns {*}
 */
GLOBAL.Lang.trim = function (ostr) {
    "use strict";
    return ostr ? ostr.replace(/^\s+|\s+$/g, "") : ostr;
};

/**
 * @description 是 undefined 吗？
 * @param {*} s
 * @return {Boolean}
 */
GLOBAL.Lang.isUndefined = function (s) {
    "use strict";
    return Object.prototype.toString.call(s) === '[object Undefined]' || s === undefined;
};

/**
 * @description 是 null 吗？
 * @param {*} s
 * @return {Boolean}
 */
GLOBAL.Lang.isNull = function (s) {
    "use strict";
    return Object.prototype.toString.call(s) === '[object Null]' || s === null;
};

/**
 * @description 是 String 类型吗？
 * @param {*} s
 * @return {Boolean}
 */
GLOBAL.Lang.isString = function (s) {
    "use strict";
    return Object.prototype.toString.call(s) === '[object String]';
};

/**
 * @description 是否为空, 空的定义：null, "null", undefined, "undefined", "", "   ", [], {}
 * @param {*} str
 * @returns {Boolean}
 */
GLOBAL.Lang.isEmpty = function (o, emptyExtended) {
    "use strict";
    var foo = GLOBAL.Lang,
        isEmpty = foo.isNull(o)
            || foo.isUndefined(o)
            || foo.isString(o) && foo.trim(o) === ""
            || foo.isString(o) && (foo.trim(o) === "null" || foo.trim(o) === "undefined")
            || foo.isArray(o) && o.length === 0
            || foo.isObject(o) && foo.getPropertiesCount(o) === 0,
        emptyDefined = [];

    return isEmpty || G.contains(o, emptyDefined.concat(emptyExtended));
};

/**
 * @description 是否为空, 空的定义：null, "null", undefined, "undefined", "", "   ", [], {}
 * @param {*} str
 * @returns {Boolean}
 */
GLOBAL.Lang.isNotEmpty = function (o, emptyExtended) {
     return !GLOBAL.Lang.isEmpty(o, emptyExtended);
};

/**
 * @description 判断, scope 中是否包含 o 元素
 * @param {*} o 此变量的值范围在 -- >  Number | NaN | String completer| Boolean | null | undefined
 * @param {Array} scope
 * @returns {Boolean}
 */
GLOBAL.Lang.contains = function (o, scope) {
    "use strict";
    for (var i = 0, len = scope.length; i < len; i++) {
        if (o === scope[i]
            || GLOBAL.Lang._isNumberPrototype(o) && isNaN(o))
            return true
    }
    return false;
};

/**
 * @description 是 Array 类型吗？
 * @param {*} s
 * @return {Boolean}
 */
GLOBAL.Lang.isArray = function (s) {
    "use strict";
    return !!(s &&
        typeof s === 'object' &&
        typeof s.length === 'number' &&
        !s.propertyIsEnumerable('length'));

//    return Object.prototype.toString.call(s) === '[object Array]';
};

/**
 * @description 获取一个对象中的属性个数
 * @params {Object} o
 * @returns {Number} 属性个数
 */
GLOBAL.Lang.getPropertiesCount = function (o) {
    "use strict";
    var count = 0;
    for (var i in o) {
        if (o.hasOwnProperty(i)) {
            count++;
        }
    }
    return count;
};

GLOBAL.Lang.isObject = function (s) {
    "use strict";
    return Object.prototype.toString.call(s) === '[object Object]';
};

var G = GLOBAL;
GLOBAL.isEmpty = GLOBAL.Lang.isEmpty;