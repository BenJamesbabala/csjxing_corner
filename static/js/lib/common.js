(function($, undefined) {
    var $isFunction = $.isFunction, $isArray = $.isArray, $each = $.each;
    
    $.noConflict();
    
    
    $.extend({
        /**
         * Returns the namespace specified and creates it if it doesn't exist
         * <pre>
         * jQuery.namespace('Platform.winport');
         * * jQuery.namespace('Platform.winport', 'Platform.winport.diy');
         * </pre>
         *
         * Be careful when naming packages. Reserved words may work in some browsers
         * and not others. For instance, the following will fail in Safari:
         * <pre>
         * jQuery.namespace('really.long.nested.namespace');
         * </pre>
         * jQuery fails because "long" is a future reserved word in ECMAScript
         *
         * @method namespace
         * @static
         * @param  {collection} arguments 1-n namespaces to create.
         * @return {object}  A reference to the last namespace object created.
         */
        namespace: function() {
            var a = arguments, o, i = 0, j, d, arg;
            for (; i < a.length; i++) {
                o = window;
                arg = a[i];
                if (arg.indexOf('.')) {
                    d = arg.split('.');
                    for (j = (d[0] == 'window') ? 1 : 0; j < d.length; j++) {
                        o[d[j]] = o[d[j]] || {};
                        o = o[d[j]];
                    }
                } else {
                    o[arg] = o[arg] || {};
                }
            }
        },
        /**
         * Executes the supplied function in the context of the supplied
         * object 'when' milliseconds later.  Executes the function a
         * single time unless periodic is set to true.
         * @method later
         * @for jQuery
         * @param when {int} the number of milliseconds to wait until the fn
         * is executed.
         * @param o the context object.
         * @param fn {Function|String} the function to execute or the name of
         * the method in the 'o' object to execute.
         * @param data [Array] data that is provided to the function.  This
         * accepts either a single item or an array.  If an array is provided,
         * the function is executed with one parameter for each array item.
         * If you need to pass a single array parameter, it needs to be wrapped
         * in an array [myarray].
         * @param periodic {boolean} if true, executes continuously at supplied
         * interval until canceled.
         * @return {object} a timer object. Call the cancel() method on this
         * object to stop the timer.
         */
        later: function(when, o, fn, data, periodic) {
            when = when || 0;
            
            var m = fn, f, id;
            
            if (o && $.type(fn) === 'string') {
                m = o[fn];
            }
            
            f = (data === undefined) ? function() {
                m.call(o);
            }
 : function() {
                m.apply(o, $.makeArray(data));
            };
            
            id = (periodic) ? setInterval(f, when) : setTimeout(f, when);
            
            return {
                id: id,
                interval: periodic,
                cancel: function() {
                    if (this.interval) {
                        clearInterval(this.id);
                    } else {
                        clearTimeout(this.id);
                    }
                }
            };
        },
        /**
         * @method extendIf
         * @param {Object} target
         * @param {Object} o
         */
        extendIf: function(target, o) {
            if (o === undefined) {
                o = target;
                target = this;
            }
            for (var p in o) {
                if (typeof target[p] === 'undefined') {
                    target[p] = o[p];
                }
            }
            return target;
        },
        /**
         * 将字符串转换成hash
         * @param {Object} s
         * @param {Object} separator
         */
        unparam: function(s, separator) {
            if (typeof s !== 'string') {
                return;
            }
            var match = s.trim().match(/([^?#]*)(#.*)?$/), hash = {};
            if (!match) {
                return {};
            }
            $.each(match[1].split(separator || '&'), function(i, pair) {
                if ((pair = pair.split('='))[0]) {
                    var key = decodeURIComponent(pair.shift()), value = pair.length > 1 ? pair.join('=') : pair[0];
                    
                    if (value != undefined) {
                        value = decodeURIComponent(value);
                    }
                    
                    if (key in hash) {
                        if (!$.isArray(hash[key])) {
                            hash[key] = [hash[key]];
                        }
                        hash[key].push(value);
                    } else {
                        hash[key] = value;
                    }
                }
            });
            return hash;
        },
        /**
         * alibaba feature, use as param but not param
         * @param {Object} a
         * @param {Bolean} traditional deep recursion?
         */
        paramSpecial: function(a, traditional) {
            var s = [], add = function(key, value) {
                // If value is a function, invoke it and return its value
                value = $isFunction(value) ? value() : value;
                s[s.length] = encodeSpecial(key) + '=' + encodeSpecial(value + '');
            };
            
            // Set traditional to true for jQuery <= 1.3.2 behavior.
            if (traditional === undefined) {
                traditional = $.ajaxSettings.traditional;
            }
            
            // If an array was passed in, assume that it is an array of form elements.
            if ($isArray(a) || a.jquery) {
                // Serialize the form elements
                $each(a, function() {
                    add(this.name, this.value);
                });
                
            } else {
                // If traditional, encode the "old" way (the way 1.3.2 or older
                // did it), otherwise encode params recursively.
                for (var prefix in a) {
                    buildParams(prefix, a[prefix], traditional, add);
                }
            }
            
            // Return the resulting serialization
            return s.join('&').replace(/\//g, '%2F').replace(/#/g, '%23').replace(/\+/g, '%2B').replace(/\s/g, '+');
        },
        /*
         * 将静态方法methodize化，即让其第一个参数或指定属性为this.
         * @method methodize
         * @param  {Object}        obj     Helper对象
         * @param  {String}可选    attr    this[attr]作为对象的this
         * @param  {Array}可选     others  不methodize化的方法
         * @return {Object}        methodize化后的对象
         */
        methodize: function(obj, attr, others) {
            var ret = {};
            for (var m in obj) {
                if (others) {
                    for (var i = others.length; i--;) {
                        if (others[i] == m) 
                            break;
                    }
                }
                if (!others || others[i] != m) {
                    (function(m) {
                        ret[m] = function() {
                            var args = [].slice.call(arguments), self = attr ? this[attr] : this;
                            return obj[m].apply(obj, [self].concat(args));
                        };
                    })(m);
                }
            }
            return ret;
        }
    });

})(jQuery);
