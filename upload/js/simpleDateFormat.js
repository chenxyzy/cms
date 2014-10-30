/**
 * org.lerx.text.SimpleDateFormat
 */
var org;
if (!org) {
    org = {};
}else if (typeof org != "object") {
    throw new Error("Namespace org failed to initialize, org already exists and is not an object!");
}
if (!org.lerx) {
    org.lerx = {};
}else if (typeof org.lerx != "object") {
    throw new Error("Namespace org.lerx failed to initialize, org.lerx already exists and is not an object!");
}
if (!org.lerx.text) {
    org.lerx.text = {};
}else if (typeof org.lerx.text != "object") {
    throw new Error("Namespace org.lerx.text failed to initialize, org.lerx.text already exists and is not an object!");
}

org.lerx.text.SimpleDateFormat = function(options){
    //this.pattern = options.pattern;
    this.pattern = options;
};
/**
 * Formats a Date into a date/time string.
 * @param date the time value to be formatted into a time string.
 * @return the formatted time string.
 */
org.lerx.text.SimpleDateFormat.prototype.format = function(options){    
    var result = this.pattern;
	//var date = options.date;
	var date = options;
    var year = date.getFullYear().toString();
    result = result.replace(/(yyyy)/g, year);
	
    var month = (date.getMonth() + 1).toString();
    if (month.length === 1) 
        month = "0" + month;
    result = result.replace(/(MM)/g, month);
    result = result.replace(/(M)/g, date.getMonth());
    
    var day = date.getDate().toString();
    if (day.length === 1) 
        day = "0" + day;
    result = result.replace(/(dd)/g, day);
    result = result.replace(/(d)/g, date.getDate());
    
    var hour = date.getHours().toString();
    if (hour.length === 1) 
        hour = "0" + hour;
    result = result.replace(/(hh)/g, hour);
    result = result.replace(/(h)/g, date.getHours());
    
    var minute = date.getMinutes().toString();
    if (minute.length === 1) 
        minute = "0" + minute;
    result = result.replace(/(mm)/g, minute);
    result = result.replace(/(m)/g, date.getMinutes());
    
    var second = date.getSeconds().toString();
    if (second.length === 1) 
        second = "0" + second;
    result = result.replace(/(ss)/g, second);
    result = result.replace(/(s)/g, date.getSeconds());
	
	var millisecond = date.getMilliseconds().toString();
    if (millisecond.length === 1) 
        millisecond = "00" + millisecond;
	if (millisecond.length === 2) 
        millisecond = "0" + millisecond;
    result = result.replace(/(SSS)/g, millisecond);
    result = result.replace(/(S)/g, date.getMilliseconds());
    return result;
};
/**
 * Parses text from the given string to produce a date.
 *
 * @param source A String whose should be parsed.
 * @return A Date parsed from the string.
 * @error Parse Exception if the beginning of the specified string cannot be parsed.
 */
org.lerx.text.SimpleDateFormat.prototype.parse = function(options){
	var result = new Date(0);
    var source = options.source;
    var pattern = this.pattern;
    var startIndex = -1;
	
    startIndex = pattern.indexOf("yyyy");
    if (startIndex >= 0) {
        var year = source.substring(startIndex, startIndex + 4);
		result.setFullYear(year);
		if(result.getFullYear() != year){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
		pattern.replace(/(yyyy)/g, year);
    }
	startIndex = pattern.indexOf("MM");
    if (startIndex >= 0) {
        var month = source.substring(startIndex, startIndex + 2) - 1;
		pattern.replace(/(MM)/g, month);
		result.setMonth(month);
		if(result.getMonth() != month){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
    }
	startIndex = pattern.indexOf("dd");
    if (startIndex >= 0) {
        var day = source.substring(startIndex, startIndex + 2);
		pattern.replace(/(dd)/g, day);
		result.setDate(day);
		if(result.getDate() != day){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
    }
	startIndex = pattern.indexOf("hh");
    if (startIndex >= 0) {
        var hour = source.substring(startIndex, startIndex + 2);
		pattern.replace(/(hh)/g, hour);
		result.setHours(hour);
		if(result.getHours() != hour){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
    }
	
	startIndex = pattern.indexOf("mm");
    if (startIndex >= 0) {
        var minute = source.substring(startIndex, startIndex + 2);
		pattern.replace(/(mm)/g, minute);
		result.setMinutes(minute);
		if(result.getMinutes() != minute){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
    }
	
	startIndex = pattern.indexOf("ss");
    if (startIndex >= 0) {
        var second = source.substring(startIndex, startIndex + 2);
		pattern.replace(/(ss)/g, second);
		result.setSeconds(second);
		if(result.getSeconds() != second){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
    }
	
	startIndex = pattern.indexOf("SSS");
    if (startIndex >= 0) {
        var millisecond = source.substring(startIndex, startIndex + 3);
		pattern.replace(/(SSS)/g, millisecond);
		result.setMilliseconds(millisecond);
		if(result.getMilliseconds() != millisecond){
			throw new Error("SimpleDateFormat parse " + options.source + " error!");
		}
    }
	return result;
};
