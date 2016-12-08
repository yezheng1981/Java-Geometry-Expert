// $Header: /cvsroot/tikiwiki/tiki/lib/tiki-js.js,v 1.50.2.32 2005/10/06 14:25:13 toggg Exp $
var feature_no_cookie = 'n';

function browser() {
    var b = navigator.appName
    if (b=="Netscape") this.b = "ns"
    else this.b = b
    this.version = navigator.appVersion
    this.v = parseInt(this.version)
    this.ns = (this.b=="ns" && this.v>=5)
    this.op = (navigator.userAgent.indexOf('Opera')>-1)
    this.safari = (navigator.userAgent.indexOf('Safari')>-1)
    this.op7 = (navigator.userAgent.indexOf('Opera')>-1 && this.v>=7)
    this.ie56 = (this.version.indexOf('MSIE 5')>-1||this.version.indexOf('MSIE 6')>-1)
    this.iewin = (this.ie56 && navigator.userAgent.indexOf('Windows')>-1)
    this.iemac = (this.ie56 && navigator.userAgent.indexOf('Mac')>-1)
    this.moz = (navigator.userAgent.indexOf('Mozilla')>-1)
    this.moz13 = (navigator.userAgent.indexOf('Mozilla')>-1 && navigator.userAgent.indexOf('1.3')>-1)
    this.oldmoz = (navigator.userAgent.indexOf('Mozilla')>-1 && navigator.userAgent.indexOf('1.4')>-1 || navigator.userAgent.indexOf('Mozilla')>-1 && navigator.userAgent.indexOf('1.5')>-1 || navigator.userAgent.indexOf('Mozilla')>-1 && navigator.userAgent.indexOf('1.6')>-1)
    this.ns6 = (navigator.userAgent.indexOf('Netscape6')>-1)
    this.docom = (this.ie56||this.ns||this.iewin||this.op||this.iemac||this.safari||this.moz||this.oldmoz||this.ns6)
}

function getElementById(id) {
    if (document.all) {
	return document.getElementById(id);
    }
    for (i=0;i<document.forms.length;i++) {
	if (document.forms[i].elements[id]) {return document.forms[i].elements[id]; }
    }
}

function toggle_dynamic_var($name) {
	name1 = 'dyn_'+$name+'_display';
	name2 = 'dyn_'+$name+'_edit';
	if(document.getElementById(name1).style.display == "none") {
		document.getElementById(name2).style.display = "none";
		document.getElementById(name1).style.display = "inline";
	} else {
		document.getElementById(name1).style.display = "none";
		document.getElementById(name2).style.display = "inline";
	
	}
	
}

function chgArtType() {
        articleType = document.getElementById('articletype').value;
        typeProperties = articleTypes[articleType];

	propertyList = new Array('show_topline','y',
				 'show_subtitle','y',
				 'show_linkto','y',
				 'show_lang','y',
				 'show_author','y',
				 'use_ratings','y',
				 'heading_only','n',
				 'show_image_caption','y',
				 'show_pre_publ','y',
				 'show_post_expire','y',
				 'show_image','y'
				 );

	var l = propertyList.length;
	for (var i=0; i<l; i++) {
	    property = propertyList[i++];
	    value = propertyList[i];
	    
	    if (typeProperties[property] == value) {
		display = "";
	    } else {
		display = "none";
	    }
	    
	    if (document.getElementById(property)) {
		document.getElementById(property).style.display = display;
	    } else {
		j = 1;
		while (document.getElementById(property+'_'+j)) {
		    document.getElementById(property+'_'+j).style.display=display;
		    j++;
		}
	    }

	}
}

function chgMailinType() {
	if (document.getElementById('mailin_type').value != 'article-put') {
		document.getElementById('article_topic').style.display = "none";
		document.getElementById('article_type').style.display = "none";
	} else {
		document.getElementById('article_topic').style.display = "";
		document.getElementById('article_type').style.display = "";
	}
}

function toggleSpan(id) {
	if (document.getElementById(id).style.display == "inline") {
		document.getElementById(id).style.display = "none";
	} else {
		document.getElementById(id).style.display = "inline";
	}
}

function toggleBlock(id) {
	if (document.getElementById(id).style.display == "block") {
		document.getElementById(id).style.display = "none";
	} else {
		document.getElementById(id).style.display = "block";
	}
}

function chgTrkFld(f,o) {
	var opt = 0;
	document.getElementById('z').style.display = "none";
	for (var i = 0; i < f.length; i++) {
		var c = f.charAt(i);
		if (document.getElementById(c)) { 
			if (c == o) {
				document.getElementById(c).style.display = "block";
				document.getElementById('z').style.display = "block";
			} else {
				document.getElementById(c).style.display = "none";
			}
		}
	}
}

function multitoggle(f,o) {
	for (var i = 0; i < f.length; i++) {
		if (document.getElementById('fid'+f[i])) { 
			if (f[i] == o) {
				document.getElementById('fid'+f[i]).style.display = "block";
			} else {
				document.getElementById('fid'+f[i]).style.display = "none";
			}
		}
	}
}

function setMenuCon(foo) {
	var it = foo.split(",");
	document.getElementById('menu_url').value = it[0];
	document.getElementById('menu_name').value = it[1];
	if (it[2]) {
		document.getElementById('menu_section').value = it[2];
	} else {
		document.getElementById('menu_section').value = '';
	}
	if (it[3]) {
		document.getElementById('menu_perm').value = it[3];
	} else {
		document.getElementById('menu_perm').value = '';
	}
}

function genPass(w1, w2, w3) {
	vo = "aeiouAEU";

	co = "bcdfgjklmnprstvwxzBCDFGHJKMNPQRSTVWXYZ0123456789_$%#";
	s = Math.round(Math.random());
	l = 8;
	p = '';

	for (i = 0; i < l; i++) {
		if (s) {
			letter = vo.charAt(Math.round(Math.random() * (vo.length - 1)));

			s = 0;
		} else {
			letter = co.charAt(Math.round(Math.random() * (co.length - 1)));

			s = 1;
		}

		p = p + letter;
	}

	document.getElementById(w1).value = p;
	document.getElementById(w2).value = p;
	document.getElementById(w3).value = p;
}

function setUserModule(foo1) {
	document.getElementById('usermoduledata').value = foo1;
}

function setSomeElement(fooel, foo1) {
	document.getElementById(fooel).value = document.getElementById(fooel).value + foo1;
}

function replaceSome(fooel, what, repl) {
	document.getElementById(fooel).value = document.getElementById(fooel).value.replace(what, repl);
}

function replaceLimon(vec) {
	document.getElementById(vec[0]).value = document.getElementById(vec[0]).value.replace(vec[1], vec[2]);
}

function replaceImgSrc(imgName,replSrc) {
  document.getElementById(imgName).src = replSrc;
}

function setSelectionRange(textarea, selectionStart, selectionEnd) {
  if (textarea.setSelectionRange) {
    textarea.focus();
    textarea.setSelectionRange(selectionStart, selectionEnd);
  }
  else if (textarea.createTextRange) {
    var range = textarea.createTextRange();
    textarea.collapse(true);
    textarea.moveEnd('character', selectionEnd);
    textarea.moveStart('character', selectionStart);
    textarea.select();
  }
}
function setCaretToPos (textarea, pos) {
  setSelectionRange(textarea, pos, pos);
}
function insertAt(elementId, replaceString) {
  //inserts given text at selection or cursor position
  textarea = getElementById(elementId);
  var toBeReplaced = /text|page|area_name/;//substrings in replaceString to be replaced by the selection if a selection was done
  if (textarea.setSelectionRange) {
    //Mozilla UserAgent Gecko-1.4
    var selectionStart = textarea.selectionStart;
    var selectionEnd = textarea.selectionEnd;
    var scrollTop=textarea.scrollTop;
    if (selectionStart != selectionEnd) { // has there been a selection
	var newString = replaceString.replace(toBeReplaced, textarea.value.substring(selectionStart, selectionEnd));
    	textarea.value = textarea.value.substring(0, selectionStart)
                  + newString
                  + textarea.value.substring(selectionEnd);
      setSelectionRange(textarea, selectionStart, selectionStart + newString.length);
    }
    else  {// set caret
       textarea.value = textarea.value.substring(0, selectionStart)
                  + replaceString
                  + textarea.value.substring(selectionEnd);
      setCaretToPos(textarea, selectionStart + replaceString.length);
    }
    textarea.scrollTop=scrollTop;
  }
  else if (document.selection) {
    //UserAgent IE-6.0
    textarea.focus();
    var range = document.selection.createRange();
    if (range.parentElement() == textarea) {
      var isCollapsed = range.text == '';
      if (! isCollapsed)  {
        range.text = replaceString.replace(toBeReplaced, range.text);
        range.moveStart('character', -range.text.length);
        range.select();
      }
	else {
		range.text = replaceString;
	}
    }
  }
  else { //UserAgent Gecko-1.0.1 (NN7.0)
    setSomeElement(elementId, replaceString)
    //alert("don't know yet how to handle insert" + document);
	}
}

function setUserModuleFromCombo(id) {
	document.getElementById('usermoduledata').value = document.getElementById('usermoduledata').value
		+ document.getElementById(id).options[document.getElementById(id).selectedIndex].value;
//document.getElementById('usermoduledata').value='das';
}


function show(foo,f,section) {
	document.getElementById(foo).style.display = "block";
	if (f) { setCookie(foo, "o", section); }
}

function hide(foo,f, section) {
	if (document.getElementById(foo)) {
		document.getElementById(foo).style.display = "none";
		if (f) {
			var wasnot = getCookie(foo, section, 'x') == 'x';
			setCookie(foo, "c", section);
			if (wasnot) {
				history.go(0);
			}
		}
	}
}

function flip(foo) {
	if (document.getElementById(foo).style.display == "none") {
		show(foo);
	} else {
		if (document.getElementById(foo).style.display == "block") {
			hide(foo);
		} else {
			show(foo);
		}
	}
}

function toggle(foo) {
	if (document.getElementById(foo).style.display == "none") {
		show(foo, true, "menu");

		setCookie(foo, "o");
	} else {
		if (document.getElementById(foo).style.display == "block") {
			hide(foo, true, "menu");
			setCookie(foo, "c");
		} else {
			show(foo, true, "menu");
		}
	}
}

function setopacity(obj,opac){
   if (document.all && !is.op){ //ie
       obj.filters.alpha.opacity = opac * 100;
   }else{
       obj.style.MozOpacity = opac;
       obj.style.opacity = opac;
   }
}

function tikitabs(focus,max) {
	for (var i = 1; i < max; i++) {
		var tabname = 'tab' + i;
		var content = 'content' + i;
		//if (document.getElementById(tabname) != 'undefined') {
			if (i == focus) {
				//show(tabname);
				show(content);
				setCookie('tab',focus);
				document.getElementById(tabname).style.borderColor="black";
			} else {
				//hide(tabname);
				hide(content);
				document.getElementById(tabname).style.borderColor="white";
			}
		//}
	}
}

function setfoldericonstate(foo) {
	if (getCookie(foo, "menu", "o") == "o") {
		src = "ofo.gif";
	} else {
		src = "fo.gif";
	}
	document.getElementsByName(foo + 'icn')[0].src = document.getElementsByName(foo + 'icn')[0].src.replace(/[^\\\/]*$/, src);
}
/* foo: name of the menu
 * def: menu type (e:extended, c:collapsed, f:fixed)
 * the menu is collapsed function of its cookie: if no cookie is set, the def is used
 */
function setfolderstate(foo, def) {
	var status = getCookie(foo, "menu", "o");
        img = "fo.gif";
	if (status == "o") {
		show(foo);
		src = "o" + img;
	} else if (status != "c"  && def != 'd') {
		show(foo);
		src = "o" + img;
	}
	else {
		hide(foo);
		src = img;
	}
	document.getElementsByName(foo + 'icn')[0].src = document.getElementsByName(foo + 'icn')[0].src.replace(/[^\\\/]*$/, src);
}

function setsectionstate(foo, def, img) {
	var status = getCookie(foo, "menu", "o");
	if (status == "o") {
		show(foo);
		if (img) src = "o" + img;
	} else if (status != "c" && def != 'd') {
		show(foo);
		if (img) src = "o" + img;
	} else /*if (status == "c")*/ {
		hide(foo);
		if (img) src = img;
	}
	if (img) document.getElementsByName(foo + 'icn')[0].src = document.getElementsByName(foo + 'icn')[0].src.replace(/[^\\\/]*$/, src);
}

function icntoggle(foo, img) {
	if (!img) img = "fo.gif";
	if (document.getElementById(foo).style.display == "none") {
		show(foo, true, "menu");
		document.getElementsByName(foo + 'icn')[0].src = document.getElementsByName(foo + 'icn')[0].src.replace(/[^\\\/]*$/, 'o' + img);
		
	} else {
		hide(foo, true, "menu");
		document.getElementsByName(foo + 'icn')[0].src = document.getElementsByName(foo + 'icn')[0].src.replace(/[^\\\/]*$/, img);
	}
}

//
// set folder icon state during page load
//
function setFolderIcons() {
	var elements = document.forms[the_form].elements[elements_name];

	var elements_cnt = ( typeof (elements.length) != 'undefined') ? elements.length : 0;

	if (elements_cnt) {
		for (var i = 0; i < elements_cnt; i++) {
			elements[i].checked = document.forms[the_form].elements[switcher_name].checked;
		}
	} else {
		elements.checked = document.forms[the_form].elements[switcher_name].checked;

		;
	} // end if... else

	return true;
}     // setFolderIcons()

// Initialize a cross-browser XMLHttpRequest object.
// The object return has to be sent using send(). More parameters can be
// given.
// callback - The function that will be called when the response arrives
//		First parameter will be the status 
//		(HTTP Response Code [200,403, 404, ...])
// method - GET or POST
// url - The URL to open
function getHttpRequest( method, url )
{
	var request;

	if( window.XMLHttpRequest )
		request = new XMLHttpRequest();
	else if( window.ActiveXObject )
	{
		try
		{
			request = new ActiveXObject( "Microsoft.XMLHTTP" );
		}
		catch( ex )
		{
			request = new ActiveXObject("MSXML2.XMLHTTP");
		}
	}
	else
		return false;

	if( !request )
		return false;

	request.open( method, url, false );

	return request;
}

// name - name of the cookie
// value - value of the cookie
// [expires] - expiration date of the cookie (defaults to end of current session)
// [path] - path for which the cookie is valid (defaults to path of calling document)
// [domain] - domain for which the cookie is valid (defaults to domain of calling document)
// [secure] - Boolean value indicating if the cookie transmission requires a secure transmission
// * an argument defaults when it is assigned null as a placeholder
// * a null placeholder is not required for trailing omitted arguments
function setCookie(name, value, section, expires, path, domain, secure) {
    if (!expires) {
        var expires = new Date();
        expires.setFullYear(expires.getFullYear() + 1);
    }
	if (feature_no_cookie == 'y') {
		var request = getHttpRequest( "GET", "tiki-cookie-jar.php?" + name + "=" + escape( value ) )
		try {
			request.send('');
			//alert("XMLHTTP/set"+request.readyState+request.responseText);
			tiki_cookie_jar[name] = value;
			return true;
		}
		catch( ex )	{
			setCookieBrowser(name, value, section, expires, path, domain, secure);
			return false;
		}
	}
	else {
		setCookieBrowser(name, value, section, expires, path, domain, secure);
		return true;
	}
}
function setCookieBrowser(name, value, section, expires, path, domain, secure) {
	if (section) {
		valSection = getCookie(section);
		name2 = "@" + name + ":";
		if (valSection) {
			if (new RegExp(name2).test(valSection))
				valSection  = valSection.replace(new RegExp(name2 + "[^@;]*"), name2 + value);
			else
				valSection = valSection + name2 + value;
			setCookieBrowser(section, valSection, null, expires, path, domain, secure);
		}
		else {
			valSection = name2+value;
			setCookieBrowser(section, valSection, null, expires, path, domain, secure);
		}
		
	}
	else {
		var curCookie = name + "=" + escape(value) + ((expires) ? "; expires=" + expires.toGMTString() : "")
			+ ((path) ? "; path=" + path : "") + ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
		document.cookie = curCookie;
	}
}

// name - name of the desired cookie
// section - name of group of cookies or null
// * return string containing value of specified cookie or null if cookie does not exist
function getCookie(name, section, defval) {
	if( feature_no_cookie == 'y' && (window.XMLHttpRequest || window.ActiveXObject) && typeof tiki_cookie_jar != "undefined" && tiki_cookie_jar.length > 0) {
		if (typeof tiki_cookie_jar[name] == "undefined")
			return defval;
		return tiki_cookie_jar[name];
	}
	else {
		return getCookieBrowser(name, section, defval);
	}
}
function getCookieBrowser(name, section, defval) {
	if (section) {
		valSection = getCookieBrowser(section);
		if (valSection) {
			name2 = "@"+name+":";
			val = valSection.match(new RegExp(name2 + "([^@;]*)"));
			if (val)
				return unescape(val[1]);
			else
				return null;
		} else {
			return defval;
		}
	} else {
		var dc = document.cookie;

		var prefix = name + "=";
		var begin = dc.indexOf("; " + prefix);

		if (begin == -1) {
			begin = dc.indexOf(prefix);

			if (begin != 0)
				return null;

		} else begin += 2;

		var end = document.cookie.indexOf(";", begin);

		if (end == -1)
			end = dc.length;

		return unescape(dc.substring(begin + prefix.length, end));
	}
}

// name - name of the cookie
// [path] - path of the cookie (must be same as path used to create cookie)
// [domain] - domain of the cookie (must be same as domain used to create cookie)
// * path and domain default if assigned null or omitted if no explicit argument proceeds
function deleteCookie(name, section, expires, path, domain, secure) {
	if (section) {
		valSection = getCookieBrowser(section);
		name2 = "@" + name + ":";
		if (valSection) {
			if (new RegExp(name2).test(valSection)) {
				valSection  = valSection.replace(new RegExp(name2 + "[^@;]*"), "");
				setCookieBrowser(section, valSection, null, expires, path, domain, secure);
			}
		}
	}
	else {

//	if( !setCookie( name, '', 0, path, domain ) ) {
//		if (getCookie(name)) {
			document.cookie = name + "="
				+ ((path) ? "; path=" + path : "") + ((domain) ? "; domain=" + domain : "") + "; expires=Thu, 01-Jan-70 00:00:01 GMT";
//		}
	}
}

// date - any instance of the Date object
// * hand all instances of the Date object to this function for "repairs"
function fixDate(date) {
	var base = new Date(0);

	var skew = base.getTime();

	if (skew > 0)
		date.setTime(date.getTime() - skew);
}

//
// Expand/collapse lists
//
function flipWithSign(foo) {
	if (document.getElementById(foo).style.display == "none") {
		show(foo);

		collapseSign("flipper" + foo);
		setCookie(foo, "o");
	} else {
		hide(foo);

		expandSign("flipper" + foo);
		setCookie(foo, "c");
	}
}

// set the state of a flipped entry after page reload
function setFlipWithSign(foo) {
	if (getCookie(foo) == "o") {
		collapseSign("flipper" + foo);

		show(foo);
	} else {
		expandSign("flipper" + foo);

		hide(foo);
	}
}

function expandSign(foo) {
	document.getElementById(foo).firstChild.nodeValue = "[+]";
}

function collapseSign(foo) {
	document.getElementById(foo).firstChild.nodeValue = "[-]";
} // flipWithSign()

//
// Check / Uncheck all Checkboxes
//
function switchCheckboxes(tform, elements_name, state) {
	// checkboxes need to have the same name elements_name
	// e.g. <input type="checkbox" name="my_ename[]">, will arrive as Array in php.
	for (var i = 0; i < tform.length; i++) {
		if (tform.elements[i].name == elements_name) {
			tform.elements[i].checked = state
		}
	}
	return true;
}  

//
// Set client offset (in minutes) to a cookie to avoid server-side DST issues
// Added 7/25/03 by Jeremy Jongsma (jjongsma@tickchat.com)
//
var expires = new Date();
var offset = -(expires.getTimezoneOffset() * 60);
expires.setFullYear(expires.getFullYear() + 1);
setCookie("tz_offset", offset, null, expires, "/");

// function added for use in navigation dropdown
// example :
// <select name="anything" onchange="go(this);">
// <option value="http://tikiwiki.org">tikiwiki.org</option>
// </select>
function go(o) {
	if (o.options[o.selectedIndex].value != "") {
		location = o.options[o.selectedIndex].value;

		o.options[o.selectedIndex] = 1;
	}

	return false;
}


// function:	targetBlank
// desc:	opens a new window, XHTML-compliant replacement of the "TARGET" tag
// added by: 	Ralf Lueders (lueders@lrconsult.com)
// date:	Sep 7, 2003
// params:	url: the url for the new window
//		mode='nw': new, full-featured browser window
//		mode='popup': new windows, no features & buttons

function targetBlank(url,mode) {
  var features = 'menubar=yes,toolbar=yes,location=yes,directories=yes,fullscreen=no,titlebar=yes,hotkeys=yes,status=yes,scrollbars=yes,resizable=yes';
  switch (mode) {
    // new full-equipped browser window
    case 'nw':
      break;
    // new popup-window
    case 'popup':
      features = 'menubar=no,toolbar=no,location=no,directories=no,fullscreen=no,titlebar=no,hotkeys=no,status=no,scrollbars=yes,resizable=yes';
      break;
    default:
      break;
   }
   blankWin = window.open(url,'_blank',features);
}

// function:	confirmTheLink
// desc:	pop up a dialog box to confirm the action
// added by: 	Franck Martin
// date:	Oct 12, 2003
// params:	theLink: The link where it is called from
// params: theMsg: The message to display
function confirmTheLink(theLink, theMsg)
{
    // Confirmation is not required if browser is Opera (crappy js implementation)
    if (typeof(window.opera) != 'undefined') {
        return true;
    }
                                                                                                                  
    var is_confirmed = confirm(theMsg);
    //if (is_confirmed) {
    //    theLink.href += '&amp;is_js_confirmed=1';
    //}
                                                                                                                  
    return is_confirmed;
} 

/** \brief: modif a textarea dimension
 * \elementId = textarea idea
 * \height = nb pixels to add to the height (the number can be negative)
 * \width = nb pixels to add to the width
 * \formid = form id (needs to have 2 input rows and cols
 **/
function textareasize(elementId, height, width, formId) {
	textarea = document.getElementById(elementId);
	form1 = document.getElementById(formId);
	if (textarea && height != 0 && textarea.rows + height > 5) {
		textarea.rows += height;
		if (form1.rows)
			form1.rows.value = textarea.rows;
	}
	if (textarea && width != 0 && textarea.cols + width > 10) {
		 textarea.cols += width;
		if (form1.cols)
			form1.cols.value = textarea.cols;
	}
}


/** \brief: insert img tag in textarea
 *	
 */	
function insertImg(elementId, fileId, oldfileId) {
    textarea = getElementById(elementId);
    fileup   = getElementById(fileId);    
    oldfile  = getElementById(oldfileId);    
    prefixEl = getElementById("prefix");    
    prefix   = "img/wiki_up/";

    if (!textarea || ! fileup) 
	return;

    if ( prefixEl) { prefix= prefixEl.value; }

    filename = fileup.value;
    oldfilename = oldfile.value;

    if (filename == oldfilename ||
	filename == "" ) { // insert only if name really changed
	return;
    }
    oldfile.value = filename;

    if (filename.indexOf("/")>=0) { // unix
	dirs = filename.split("/"); 
	filename = dirs[dirs.length-1];
    }
    if (filename.indexOf("\\")>=0) { // dos
	dirs = filename.split("\\"); 
	filename = dirs[dirs.length-1];
    }
    if (filename.indexOf(":")>=0) { // mac
	dirs = filename.split(":"); 
	filename = dirs[dirs.length-1];
    }
    // @todo - here's a hack: we know its ending up in img/wiki_up. 
    //      replace with dyn. variable once in a while to respect the tikidomain 
    str = "{img src=\"img/wiki_up/" + filename + "\" }";
    insertAt(elementId, str);
}

/*
 * opens wiki 3d browser
 */
function wiki3d_open (page, width, height) {
    window.open('tiki-wiki3d.php?page='+page,'wiki3d','width='+width+',height='+height+',scrolling=no');
}

// This was added to allow wiki3d to change url on tiki's window
window.name = 'tiki';

