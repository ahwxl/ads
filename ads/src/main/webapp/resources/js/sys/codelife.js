/**
 * Ext.ux.CodeLife
 * @version  1.1
 * @author Doug Hendricks doug[always-At]theactivegroup.com
 * @copyright 2007-2009
 * @description  An unobtrustive, multi-purpose Text Highlighter (for use with the Extjs framework only)
 *
 * Used for code( or wiki) highlighting content rendered on the client browser.
 * No server-side code support is required.
 *
 * based on previous work by:
 *
 *     Dan Webb 11/2005  http://www.danwebb.net
 * and Dean Edwards      http://dean.edwards.name/my/behaviors/#star-light.htc

 ************************************************************************************
 *   This file is distributed on an AS IS BASIS WITHOUT ANY WARRANTY;
 *   without even the implied warranty of MERCHANTABILITY or
 *   FITNESS FOR A PARTICULAR PURPOSE.
 ************************************************************************************

 @license CodeLife is licensed under the terms of the Open Source GPL 3.0 license.

 Usage:
  Add a script tag for this script and any stylesets you need to use
  to the page in question, add correct class names to container (ie PRE or CODE) elements,
  and define CSS styles for defined styleSets.

 Sample CSS:
  ----------------------------------------------------------------------------------------
    pre, code, .pre { overflow:visible; white-space:pre;}

    .codelife span.comment, .codelife span.blockcomment, .codelife span.sgmlcomment {
       color: green;font-style:italic;}

    .codelife span.string {  color:blue;font-weight:550;}

    .javascript span.brackets {color: #ff0000;}
    .javascript span.symbol { color: #6BCFF7;}
    .javascript span.keywords {color: #0000ff;}
    .javascript span.global { color: #1e90ff;}
    .javascript span.regexp { color: #ff8c00;}
    .javascript span.objects { color: #ff0000;}
    .javascript span.Ext { color: #800080;}


    a.cl-url { color: #fa8072;background-color: #ffffe0;}

    span.cl-email {
        background-image: url(../resources/images/email.png);
        background-position: left;
        background-repeat: no-repeat;
        height: 25px;
        width: 52px;
        text-align: right;
        padding-left:20px;
        padding-top:3px;
        padding-bottom:2px;
        padding-right:2px;
        background-color: #ffffe0;
         }
  ----------------------------------------------------------------------------------------

  // then decorate all <pre> elements with the classname 'javascript' when the DOM is ready:
  Ext.onReady( function(){

    var hi      = new Ext.ux.CodeLife();
    var hiproto = Ext.ux.CodeLife.prototype;  //helper for canned rule expressions

   //define a styleset for javascript syntax

    hi.setStyle("javascript",{
        rules:{
          string    : hiproto.$$STRING
         ,regexp    : hip.$$REGEXP
         ,comment   : hiproto.$$JSLINECOMMENT
         ,blockcomment  : hiproto.$$BLOCKCOMMENT
         ,brackets  : { exp : /\(|\)|\]|\[/  }
         ,keywords  : { exp : /\b(arguments|break|callee|case|catch|continue|default|delete|do|else|false|finally|for|forEach|function|if|in|instanceof|indexOf|new|null|return|switch|this|throw|true|try|typeof|var|void|while|with)\b/ }
         ,global    : { exp : /\b(apply|alert|eval|execScript|call|asString|toString|valueOf|window|element|prototype|constructor|document|escape|unescape|encodeURI|decodeURI|encodeURIComponent|decodeURIComponent|parseInt|parseFloat|setTimeout|clearTimeout|setInterval|clearInterval|NaN|isNaN|Infinity|undefined)\b/ }
         ,objects   : { exp : /\b(Object|Function|String|Element|Number|Date|Array|Node|Window|RegExp|Event)\b/}

        }
        ,escapeChar : '\\'
        ,numberedLines : true
        });



     hi.highlightElements('javascript','pre.javascript');
 });

   starter styleSets are defined below for:   javascript, css, html, xml, ruby, TSQL, vbs, and php

   Create your own for any language, text format, code generation... ( even new ones XUL? wiki's? )

 */


Ext.namespace("Ext.ux");

(function(){

// replace errant callback support for safari.
if ("a".replace(/a/, function() {return "b"}) != "b") {

    var default_replace = String.prototype.replace;
    String.prototype.replace = function(search,replace){
        // replace is not function
        if(typeof replace != "function"){
            return default_replace.apply(this,arguments);
        }
        var str = "" + this;
        var callback = replace, idx;

        // search string is not RegExp
        if(!(search instanceof RegExp)){
            idx = str.indexOf(search);
            return ( idx == -1 ? str :default_replace.apply(str,[search,callback(search, idx, str)]) );
        }
        var reg = search
           ,result = []
           ,lastidx = reg.lastIndex
           ,re;

        while((re = reg.exec(str)) !== null){
            idx = re.index;
            var args = re.concat(idx, str);

            result.push(str.slice(lastidx,idx), callback.apply(null,args).toString() );
            if(!reg.global){
                lastidx += RegExp.lastMatch.length;
                break;
            }else{
                lastidx = reg.lastIndex;
            }
        }
        result.push(str.slice(lastidx));
        return result.join("");
       };

}


// used to determine nesting levels
var  $GROUPS = /\(/g
    ,$SUB_REPLACE = /\$\d/
    ,$INDEXED = /^\$\d+$/
    ,$TRIM = /(['"])\1\+(.*)\+\1\1$/
    ,$$ESCAPE = /\\./g
    ,$QUOTE = /'/
    ,$$DELETED = /\001[^\001]*\001/g ;

 function $DELETE(match, offset)
    {return "\001" + match[offset] + "\001"};

 // encode special characters
 function _encode(text) {
    return text.split("\x02").join("&lt;").split("\x03").join("&amp;");
 };

 // decode special characters
 function _decode(text) {
    // patterns need "<" encoded
    return text.split("<").join("\x02").split("&").join("\x03");
 };

 // encode escaped characters
var _escaped = [];
function _escape(string, escapeChar) {
    return escapeChar ? string.replace(new RegExp("\\" + escapeChar + "(.)", "g"), function(match, ch) {
        _escaped[_escaped.length] = ch;
        return escapeChar;
    }) : string;
};
// decode escaped characters
function _unescape(string, escapeChar) {
    var i = 0;
    return escapeChar ? string.replace(new RegExp("\\" + escapeChar, "g"), function() {
        return escapeChar + (_escaped[i++] || "");
    }) : string;
};
function _internalEscape(text) {
    return text.replace($$ESCAPE, "");
};

var _addRule = function(className, rule) {
    // add a replace rule
    var exp = _decode((typeof rule.exp != "string")
          ?String(rule.exp).substr(1, String(rule.exp).length-2)
          :rule.exp);

    // converts regex rules to strings and chops off the slashes

    this.rules.push({
             className : className
            ,exp : "(" + exp + ")"
            ,length : (exp.match( /(^|[^\\])\([^?]/g) || "").length + 1 // number of subexps in rule
            ,replacement : rule.replacement|| null
        });


   }
   // main text parsing and replacement
  , _parse =  function(text, rules, ignoreCase) {

       return text.replace(new RegExp(rules,(ignoreCase)?"gi":"g"), function() {
        var i = 0, j = 1, rule;
        while (rule = rules[i++]) {
             if (arguments[j]) {
                // if no custom replacement defined do the simple replacement
                if (!rule.replacement) {
                    return '<span class=\"' + rule.className + '\">' + arguments[0] + '</span>';
                } else if (typeof rule.replacement == "function"){  //custom
                     return rule.replacement.call(null, rule.className,arguments);
                 } else {
                    // replace $0 with the className then do normal global replaces
                    var str = rule.replacement.replace(/([\$]0)/g, rule.className);
                    for (var k = 1; k <= rule.length - 1; ++k){
                        str = str.replace(new RegExp('([\$]'+k+')',"g"), arguments[j + k]);
                      }

                    return str;
                }
            } else { j+= rule.length; }
        }
     });
 }

 ,_whiteSpace = function(text, tabLength, numbered, styledRows) {
    var te = tabLength || 4
        ,leader = /(\n[&nbsp;]*) /g
        ,tStop = '';

    while (leader.test(text)) {
        text = text.replace(leader, "$1&#160;");
    }

    if(!!te){  //tab expansion
        while (te--) {tStop += "&#160;";}
        text = text.replace(/\t/g, tStop);
    }

    return text;

 }
 ,_getNodeText = function(parent) {
        var text = "";
        // loop through text nodes
        var childNodes = parent.childNodes;
        for (var i = 0, node; (node = childNodes[i]); ++i) {
            switch (node.nodeType) {
                case 1:   //ELEMENT_NODE
                    text += (node.tagName == "BR") ? "\r" : _getNodeText(node);
                    break;
                case 3:  // TEXT_NODE:
                    text += node.nodeValue;
                    break;
            }
        }
        node = null;
        // normalize, ensure we have the same text for both platforms (mozilla/explorer)
        return text.replace(/\r/g, '\n') + '\n';
   };


Ext.ux.CodeLife = function(config){

 this.styleSets = {};
 this.rules = [];

 Ext.apply(this,config||{});

 this.rules.toString = function() {
             // joins regexes into one big parallel regex
             var exps = [];
             for (var i = 0; i < this.length; ++i)
                { exps.push(this[i].exp);}
             return exps.join("|");
      };

};

Ext.override(Ext.ux.CodeLife,{

   // used internally to parse email addresses
   // and http URLs, thus they require '/gi' option
    $$HTTPURL: /(https?:\/\/+[\w\/\-%&:#=.,?+$]+)/gi
   ,$$EMAILS : /([\w.-]+@[\w.\-]+\.\w+)/gi

   ,setStyle : function(name, styleSet) {

      this.styleSets[name] = Ext.apply(  //styleSet defaults
               { name : name
            ,rules : {}
            ,ignoreCase : false
            ,escapeChar : ''
            ,parseEmails : true
            ,parseLinks : true
            ,tabStops   : 4

            },styleSet || {});

    }
    ,removeStyle : function(name) {

          if(this.styleSets[name]){
             delete this.styleSets[name];
          }
    }

    ,highlightElements : function(styleSetName, query ,clearClass, parent) {

        if(!this.styleSets[styleSetName]){return null;}

        this.setRules(styleSetName);

        clearClass || (clearClass = false);

        var styleSet = this.styleSets[styleSetName];

        //Accept an Ext.Element reference or a Ext.query pattern
        var Els = [].concat( Ext.get(query) || Ext.query(query || '.'+styleSetName, parent? Ext.getDom(parent): document));

        Ext.each(Els, function(el){
            var eld;
            if(eld = Ext.getDom(el)){
                var target= Ext.fly(eld, '_codelife');
                target.update( //'<code class="pre">' +
                    this.highlightFragment(styleSetName, _getNodeText(eld))
                    //+ '</code>'
                    ) ;
                target.addClass(['codelife',styleSetName]);
                clearClass && target.removeClass(styleSetName);
                target = null;
            }
        },this);
        delete Ext.Element._flyweights['_codelife']; //orphan reference cleanup

        return Els;

   }

   ,highlightFragment : function(styleSetName,fragment) {

        if(!this.styleSets[styleSetName]){ return fragment; }

        var styleSet = this.styleSets[styleSetName];

        //var styleSets = this.styleSets;

        _escaped = [];

        this.rules.length || this.setRules(styleSetName);

        fragment= _unescape(_parse(_escape(_decode(fragment), styleSet.escapeChar), this.rules, styleSet.ignoreCase),styleSet.escapeChar);

        if(styleSet.parseLinks){
            fragment = fragment.replace(this.$$HTTPURL,
                '<a class=\"cl-url\" href=\"$1\" target=\"_codelifepop\">$1</a>' );
        }

        if(styleSet.parseEmails){
            fragment = fragment.replace(this.$$EMAILS ,
                '<a class=\"cl-email\" href=\"mailto:$1\"><span class=\"cl-email\">$1</span></a>');
        }

        return _whiteSpace(_encode(fragment));

   }
   ,setRules:function(styleSetName, preserve){
        var styleSet = this.styleSets[styleSetName];
        if(!preserve){
            this.rules.length = 0;  // clear rules array
        }
        for (var className in styleSet.rules) {
                if(styleSet.rules.hasOwnProperty(className)){
                        _addRule.call(this, className, styleSet.rules[className]);
                  }
            }
   }
 });


Ext.CodeLife = new Ext.ux.CodeLife();

var hip=Ext.ux.CodeLife.prototype;

  //Add some canned pattern/replacement prototypes for code highlighting
Ext.apply(hip,{

    $$STRING        : {exp: '\'[^\']*\'|\"[^\"]*\"' }
   ,$$JSLINECOMMENT : {exp:  /(\/\/[^\n]+)/ }
   ,$$BLOCKCOMMENT  : {exp: /(\/\*[^*]*\*+([^\/][^*]*\*+)*\/)/
            ,replacement: function(className, matches){ //instead of complex backreferences
                return matches[0].replace(/^.*/gm, '<span class=\"'+className+'\">$&</span>');
            }
    }
   ,$$SGMLCOMMENT   :  {exp: /<!\s*(--([^-]|[\r\n]|-[^-])*--\s*)>/
            ,replacement: function(className, matches){
                    return matches[0].replace(/^.*/gm, '<span class=\"'+className+'\">$&</span>');
            }
    }
   ,$$REGEXP        : { exp : /([^\w\$\/'"*)])(\/[^\/\n\r\*][^\/\n\r]*\/g?i?m?)/
            ,replacement:  '$1<span class=\"$0\">$2</span>'}
   }); //end canned

Ext.CodeLife.setStyle("javascript",{
        rules:{
          string    : hip.$$STRING
         ,regexp    : hip.$$REGEXP
         ,comment   :  hip.$$JSLINECOMMENT
         ,blockcomment  :  hip.$$BLOCKCOMMENT
         ,brackets  : { exp : /\(|\)|\]|\[/  }
         ,keywords  : { exp : /\b(arguments|break|callee|case|catch|continue|default|delete|do|else|false|finally|for|forEach|function|if|in|instanceof|indexOf|new|null|return|switch|this|throw|true|try|typeof|var|void|while|with)\b/ }
         ,global    : { exp : /\b(apply|alert|eval|execScript|call|asString|toString|valueOf|element|prototype|constructor|document|escape|unescape|encodeURI|decodeURI|encodeURIComponent|decodeURIComponent|parseInt|parseFloat|setTimeout|clearTimeout|setInterval|clearInterval|NaN|isNaN|Infinity|undefined)\b/ }
         ,objects   : { exp : /\b(Object|Function|String|Element|Number|Date|Array|Node|window|document|RegExp|Event)\b/}

        }
        ,escapeChar : '\\'
        });

Ext.CodeLife.setStyle("Ext",{
         rules: {
            objects   : { exp : /\b(fly|Ext.select|Ext.get|Ext|getEl|dom|superclass|extendX|extend|override|applyIf|createDelegate|defer|createCallback|createInterceptor|createSequence|format|leftPad|toggle|trim)\b/}
         }
        ,escapeChar : '\\'
        });

Ext.CodeLife.setStyle("css", {
        rules:{

          blockcomment  :  hip.$$BLOCKCOMMENT
         ,selectors     : {exp : /([\w-:\[.#][^{};]*)\{/ ,replacement : '<span class=\"$0\">$1</span>{' }
         ,proprietary   : {exp : /(-[\w-]+)\s*:/ ,replacement : '<span class=\"$0\">$1</span>:'}
         ,properties    : {exp :  /([\w-]+)\s*:/ , replacement : '<span class=\"$0\">$1</span>:'}
         ,keywords      : {exp : /@\w[\w\s]*/ }
         ,units         : {exp : /([0-9])(em|en|px|%|pt)\b/ ,replacement : '$1<span class=\"$0\">$2</span>'}
         ,urls          : {exp : /url\([^\)]*\)/}
         ,important     : {exp : /!\b(\important)\b/ }
         ,colors    : {exp : /(#[\da-fA-F0-9]{3,};?)/ ,replacement : '<a class=\"$0 colorized\" style=\"color: $1\">$1</a>'}
        }
        ,escapeChar : '\\'
        ,ignoreCase : false
        });

Ext.CodeLife.setStyle("html", {
        rules:{
          comment    :  hip.$$SGMLCOMMENT
         ,tag        : {exp: /(<\/?)(\w+)/ ,replacement: '$1<span class=\"$0\">$2</span>'}
         ,string     :  hip.$$STRING
         ,attribute  : {exp: /\s[\w:-]+=/ }
         ,doctype    : {exp: /<!DOCTYPE[^>]+>/ }
         ,entity     : {exp: /&#?\w+;/}
         ,stylescript : {exp: /<style[^>]*>[\w|\t|\r|\W]*<\/style>/  }

        }
        ,ignoreCase : false

        });

Ext.CodeLife.setStyle("xml", {
        rules:{
          comment    : hip.$$SGMLCOMMENT
         ,tag        : {exp: /(<\/?)([\w:-]+)/ ,replacement: '$1<span class=\"$0\">$2</span>'}
         ,tagext     : {exp: /(\/)>/ ,replacement: '<span class=\"$0\">$1</span>'}
         ,attribute  : {exp: /\s[\w:-]+=/ }
         ,pi         : {exp: /<\?[\w-]+[^>]+>/ }
         ,doctype    : {exp: /<!DOCTYPE[^>]+>/ }
         ,entity     : {exp: /&#?\w+;/}
         ,string     :  hip.$$STRING
         ,cdata      : {exp: /<!\[CDATA\[([^]]*]([^]]+])*]+([^]>][^]]*]([^]]+])*]+)*>)?/ , replacement : '$2<span class=\"$0\">$4</span>>'}
        }
        ,ignoreCase : false
        ,tabStops   : 1

        });

Ext.CodeLife.setStyle("tsql", {
        rules:{
          comment  : {exp : /(--)[^\n]*/ }
         ,blockcomment  :  hip.$$BLOCKCOMMENT
         ,string     :  hip.$$STRING
         ,keywords : {exp : /\b(ADD|ALL|ALTER|AND|ANY|AS|ASC|AUTHORIZATION|BACKUP|BEGIN|BETWEEN|BREAK|BROWSE|BULK|BY|CASCADE|CASE|CHECK|CHECKPOINT|COALESCE|COLLATE|COLUMN|COMMIT|COMPUTE|CONSTRAINT|CONTAINS|CLOSE|CLUSTERED|CONTAINSTABLE|CONTINUE|CONVERT|CREATE|CROSS|CURRENT|CURRENT_DATE|CURRENT_TIME|CURRENT_TIMESTAMP|CURRENT_USER|CURSOR|DATABASE|DBCC|DEALLOCATE|DECLARE|DEFAULT|DELETE|DENY|DESC|DISK|DISTINCT|DISTRIBUTED|DOUBLE|DROP|DUMMY|DUMP|ELSE|END|ERRLVL|ESCAPE|EXCEPT|EXEC|EXECUTE|EXISTS|EXIT|FETCH|FILE|FILLFACTOR|FOR|FOREIGN|FREETEXT|FREETEXTTABLE|FROM|FULL|FUNCTION|GOTO|GRANT|GROUP|HAVING|HOLDLOCK|IDENTITY|IDENTITY_INSERT|IDENTITYCOL|IF|IN|INDEX|INNER|INSERT|INTERSECT|INTO|IS|JOIN|KEY|KILL|LEFT|LIKE|LINENO|LOAD|NATIONAL|NOCHECK|NONCLUSTERED|NOT|NULL|NULLIF|OF|OFF|OFFSETS|ON|OPEN|OPENDATASOURCE|OPENQUERY|OPENROWSET|OPENXML|OPTION|OR|ORDER|OUTER|OVER|PERCENT|PLAN|PRECISION|PRIMARY|PRINT|PROC|PROCEDURE|PUBLIC|RAISERROR|READ|READTEXT|RECONFIGURE|REFERENCES|REPLICATION|RESTORE|RESTRICT|RETURN|REVOKE|RIGHT|ROLLBACK|ROWCOUNT|ROWGUIDCOL|RULE|SAVE|SCHEMA|SELECT|SESSION_USER|SET|SETUSER|SHUTDOWN|SOME|STATISTICS|SYSTEM_USER|TABLE|TEXTSIZE|THEN|TO|TOP|TRAN|TRANSACTION|TRIGGER|TRUNCATETSEQUAL|UNION|UNIQUE|UPDATE|UPDATETEXT|USE|USER|VALUES|VARYING|VIEW|WAITFOR|WHEN|WHERE|WHILE|WITH|WRITETEXT)\b/ }
         ,datatypes :{exp : /\b(bigint|int|smallint|tinyint|bit|decimal|numeric|money|smallmoney|float|real|datetime|smalldatetime|char|varchar|textnchar|nvarchar|ntext|binary|varbinary|image|cursor|sql_variant|table|timestamp|uniqueidentifier)\b/ }
         ,global    :{exp: /\b(@@DATEFIRST|@@DBTS|@@LANGID|@@LANGUAGE|@@LOCK_TIMEOUT|@@MAX_CONNECTIONS|@@MAX_PRECISION|@@NESTLEVEL|@@OPTIONS|@@REMSERVER|@@SERVERNAME|@@SERVICENAME|@@SPID|@@TEXTSIZE|@@VERSION|@@CURSOR_ROWS|CURSOR_STATUS|@@FETCH_STATUS|DATEADD|DATEDIFF|DATENAME|DATEPART|DAY|GETDATE|GETUTCDATE|MONTH|YEAR|ABS|DEGREES|RAND|ACOS|EXP|ROUND|ASIN|FLOOR|SIGN|ATAN|LOG|SIN|ATN2|LOG10|SQUARE|CEILING|PI|SQRT|COS|POWER|TAN|COT|RADIANS|COL_LENGTH|fn_listextendedproperty|COL_NAME|FULLTEXTCATALOGPROPERTY|COLUMNPROPERTY|FULLTEXTSERVICEPROPERTY|DATABASEPROPERTY|INDEX_COL|DATABASEPROPERTYEX|INDEXKEY_PROPERTY|DB_ID|INDEXPROPERTY|DB_NAME|OBJECT_ID|FILE_ID|OBJECT_NAME|FILE_NAME|OBJECTPROPERTY|FILEGROUP_ID|@@PROCID|FILEGROUP_NAME|SQL_VARIANT_PROPERTY|FILEGROUPPROPERTY|TYPEPROPERTY|FILEPROPERTY|fn_trace_geteventinfo|IS_SRVROLEMEMBER|fn_trace_getfilterinfo|SUSER_SID|fn_trace_getinfo|SUSER_SNAME|fn_trace_gettable|USER_ID|HAS_DBACCESS|USER|IS_MEMBER|ASCII|NCHAR|SOUNDEX|CHAR|PATINDEX|SPACE|CHARINDEX|REPLACE|STR|DIFFERENCE|QUOTENAME|STUFF|LEFT|REPLICATE|SUBSTRING|LEN|REVERSE|UNICODE|LOWER|RIGHT|UPPER|LTRIM|RTRIM|APP_NAME|CASE|CAST|CONVERT|COALESCE|COLLATIONPROPERTY|CURRENT_TIMESTAMP|CURRENT_USER|DATALENGTH|@@ERROR|fn_helpcollations|fn_servershareddrives|fn_virtualfilestats|FORMATMESSAGE|GETANSINULL|HOST_ID|HOST_NAME|IDENT_CURRENT|IDENT_INCR|IDENT_SEED|@@IDENTITY|IDENTITY|ISDATE|ISNULL|ISNUMERIC|NEWID|NULLIF|PARSENAME|PERMISSIONS|@@ROWCOUNT|ROWCOUNT_BIG|SCOPE_IDENTITY|SERVERPROPERTY|SESSIONPROPERTY|SESSION_USER|STATS_DATE|SYSTEM_USER|@@TRANCOUNT|USER_NAME|@@CONNECTIONS|@@PACK_RECEIVED|@@CPU_BUSY|@@PACK_SENT|fn_virtualfilestats|@@TIMETICKS|@@IDLE|@@TOTAL_ERRORS|@@IO_BUSY|@@TOTAL_READ|@@PACKET_ERRORS|@@TOTAL_WRITE|PATINDEX|TEXTPTR|TEXTVALID)\b/ }
             }
        ,escapeChar : '\\'
        });

Ext.CodeLife.setStyle("vbs", {
        rules:{
          comment  : {exp : /(rem|')[^\n]+/ }
         ,string     :hip.$$STRING
         ,keywords : {exp : /\b(call|class|end|const|dim|do|loop|erase|execute|executeglobal|exit|for|each|in|next|function|end\\sfunction|if|then|else|on\\serror|resume\\snext|goto\\s0|goto\\s1|option\\sexplicit|private|property\\sget|property\\slet|property\\sset|end\\sproperty|public|randomize|redim|select|end\\sselect|case|set|sub|end\\ssub|while|wend|with|null|nothing|me|true|false|and|or|not|xor)\b/ }
         ,global   : {exp : /\b(Abs|Array|Asc|Atn|CBool|CByte|CCur|CDate|CDbl|Chr|CInt|CLng|CStr|Cos|CreateObject|CSng|Date|DateAdd|DateDiff|DatePart|DateSerial|DateValue|Day|Eval|Exp|Filter|FormatCurrency|FormatDateTime|FormatNumber|FormatPercent|GetLocale|GetObject|GetRef|Hex|Hour|InputBox|InStr|InStrRev|Int|Fixs|IsArray|IsDate|IsEmpty|IsNull|IsNumeric|IsObject|Join|LBound|LCase|Left|Len|LoadPicture|Log|LTrim|RTrim|Trim|Maths|Mid|Minute|Month|MonthName|MsgBox|Now|Oct|Replace|RGB|Right|Rnd|Round|ScriptEngine|ScriptEngineBuildVersion|ScriptEngineMajorVersion|ScriptEngineMinorVersion|Second|SetLocale|Sgn|Sin|Space|Split|Sqr|StrComp|String|Tan|Time|Timer|TimeSerial|TimeValue|TypeName|UBound|UCase|VarType|Weekday|WeekdayName|Year)\b/ }
         ,constants: {exp : /\b(vbBlack|vbRed|vbGreen|vbYellow|vbBlue|vbMagenta|vbCyan|vbWhite|vbSunday|vbMonday|vbTuesday|vbWednesday|vbThursday|vbFriday|vbSaturday|vbUseSystemDayOfWeek|vbFirstJan1|vbFirstFourDays|vbFirstFullWeek|vbGeneralDate|vbLongDate|vbShortDate|vbLongTime|vbShortTime|vbObjectError|vbOKOnly|vbOKCancel|vbAbortRetryIgnore|vbYesNoCancel|vbYesNo|vbRetryCancel|vbCritical|vbQuestion|vbExclamation|vbInformation|vbDefaultButton1|vbDefaultButton2|vbDefaultButton3|vbDefaultButton4|vbApplicationModal|vbSystemModal|vbOK|vbCancel|vbAbort|vbRetry|vbIgnore|vbYes|vbNo|vbCr|VbCrLf|vbFormFeed|vbLf|vbNewLine|vbNullChar|vbNullString|vbTab|vbVerticalTab|vbUseDefault|vbTrue|vbFalse|vbEmpty|vbNull|vbInteger|vbLong|vbSingle|vbSingle|vbCurrency|vbDate|vbString|vbObject|vbError|vbBoolean|vbVariant|vbDataObject|vbDecimal|vbByte|vbArray|Class_Initialize|Class_Terminate|Clear|Execute|Raise|Replace|Test|Err|RegExp|escape|unescape)\b/ }
         ,regexp   : hip.$$REGEXP
             }
            ,escapeChar : '\\'
        });

Ext.CodeLife.setStyle("php", {
        rules:{
          comment  :  hip.$$JSLINECOMMENT
         ,blockcomment  : hip.$$BLOCKCOMMENT
         ,string     : hip.$$STRING
         ,keywords : {exp : /\b(as|break|class|case|continue|extends|declare|default|define|do|echo|else|elseif|else if|endif|endfor|endswitch|endwhile|exit|for|foreach|function|global|goto|if|include|include_once|next|return|require|require_once|switch|while)\b/ }
         ,global   : {exp : /\b(call_user_func|header|print|_POST|_GET|fopen|fclose|fpassthru|file_exists|is_readable|__isset|strlen|substr)\b/ }
         ,datatypes :{exp : /\b(array|bool|integer|float|mixed|object|string|void|NULL)\b/ }
         ,variable : {exp : /$\w+/ }
         ,regexp   : hip.$$REGEXP
             }
            ,escapeChar : '\\'
        });

Ext.CodeLife.setStyle("ruby", {
        rules:{
         comment : {exp  : /#[^\n]+/ }
        ,brackets: {exp  : /\(|\)/ }
        ,string  :  hip.$$STRING
        ,keywords: {exp  : /\b(do|end|self|class|def|if|module|yield|then|else|for|until|unless|while|elsif|case|when|break|retry|redo|rescue|require|raise)\b/ }
        ,symbol  : {exp : /([^:])(:[A-Za-z0-9_!?]+)/ }
        }
        ,escapeChar : '\\'
        });
        
})();