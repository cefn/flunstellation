var numBalloons = 0;

var cameraSelector = "select[name='viewer.cameraName']";

function getGlobal(){
	return (function(){return this;})();
}

var missingJavaNotified = false;
function sendToJava(command, data){
	if("sendNSCommand" in getGlobal()){
		sendNSCommand(command, JSON.stringify(data));
	}
	else{
		if(!missingJavaNotified){
			alert("This page is intended to be viewed within a JWebBrowser instance in a Flunstellas application");			
			missingJavaNotified = true;
		}
	}
}

function blankGraph(){
	//reset the balloon counter
	numBalloons = 0;
	//blank all fields and sliders
	$("#graphtab, #balloonstab, #viewertab")
		.find("input, textarea").val("")
		.find("slider").slider({value:0});
	//remove balloons from accordion
	$("#balloonsholder").empty();
	
	//blank various visible fields
	setPath("graph.title", "Untitled");
	setPath("graph.description", "Add your description here");
	setPath("graph.author.title", "Identify yourself");
}

function addCameraName(cameraName){
	var selectq = $(cameraSelector);
	var option = $("<option></option>");
	option.attr("value", cameraName);
	option.text(cameraName);
	selectq.append(option);
}

function addBalloon(){
	numBalloons++;
	//count existing balloons
	var balloonsHolder = $("#balloonsholder");
	//create and prepare template content
	var newBalloon = $("#balloonstemplate").clone().removeAttr("id");
	newBalloon.find("input[name], textarea[name], .slider[name], span[name]").each(function(){
		//prefix name with unique indexed path for this balloon (JXPath indexed from 1 not 0)
		$(this).attr("name", "graph.balloons[" + numBalloons + "]." + $(this).attr("name"));
	});
	//attach to document
	balloonsHolder.append(newBalloon);
	addDynamism(newBalloon);
	doLayout();
}

function editBalloon(position){
	setEditMode(true); //start editing
	$(".tabs").tabs("select", 1); //balloon tab
	$(".accordion").accordion("select", position); //correct accordion
}

function setPath(path,value){
	
	//override certain values if blank
	if(value == null || value == ""){
		if(path.indexOf("title") != -1){
			value = "Untitled";
		}		
		if(path.indexOf("description") != -1){
			value = "No description yet";
		}		
	}

	//create filter clause for various jqueries
	var filter = "[name='" + path + "']";
	//set inputs and text areas to the proper value
	$("input" + filter + "," + "textarea" + filter + "," + "select" + filter).val(value);
	//set sliders to the proper value
	$(".slider" + filter).slider({value:value});
	//set spans to the proper value
	$("span" + filter).text(value);
	
}

function addDynamism(ancestor){
	
	$(ancestor).find(".media").media({ width: 240, height: 120});
	
	//TODO CH investigate use of jQuery.media.players.player.node.player.fullScreen( true )

	$(ancestor).find(".slider").each(function(){
		var sliderq = $(this);
		//find matching input field(s)
		var inputq = $("input[name='" + sliderq.attr("name") + "']");
		//figure out initial value
		var initialValue = inputq.val();
		initialValue = (!isNaN(initialValue)? initialValue:0);
		//create slider and bind events
		//slider controlling input
		var updating = false;
		sliderq.slider({
			value:initialValue,
			stop:function(event, ui){
				updating = true;
				inputq.val(ui.value).trigger("change"); //force event propagation
				updating = false;
			}
		});
		//input controlling slider
		inputq.change(function(){
			if(!updating){ //avoid infinite loop from slider setting its input
				sliderq.slider("option", {value:inputq.val()});
			}
		})
	});

	//reset ranges for sliders which bound to a single unit 
	$(ancestor).find(".slider[name*='center.height'], .slider[name*='center.radius']").slider("option",{
		min:0,
		max:1.0,
		step:0.01
	});

	//reset ranges for sliders which are for circle positions
	$(ancestor).find(".slider[name*='angle'], .slider[name*='rotation'], .slider[name*='YAngle']").slider("option",{
		min:-180,
		max:180,
		step:1
	});

	//specialise the viewer angle to 0-90
	$(ancestor).find(".slider[name='viewer.XAngle']").slider("option",{
		min:-90,
		max:0,
		step:1
	});	

	//specialise the viewer angle to 0-90
	$(ancestor).find(".slider[name='viewer.YOffset'], .slider[name='viewer.ZOffset']").slider("option",{
		min:-1.0,
		max:1.0,
		step:0.01
	});	

	//specialise the scale from 0.1 to 4.0
	$(ancestor).find(".slider[name*='scale']").slider("option",{
		min:0.1,
		max:4.0,
		step:0.1
	});	

	$(ancestor).find(".properties input, .properties textarea, .properties select").change(function(){
		var q = $(this);
		var data = {
			path:q.attr("name"),
			value:q.val() //TODO needs escaping for single quote problem
		};
		sendToJava("fieldChanged", data);
	});
	
	$(ancestor).find("input.file").focus(function(){
		var input = $(this);
		input.blur();
		var data = {
			path:input.attr("name"),
			dialog:input.attr("dialog")
		};
		sendToJava("chooseFile", data);
	});
}

var editMode = false;

function refreshAccordions(){
	$(".accordion").accordion("resize");
	$(".accordion").accordion("refresh");		
}

function resetLayout(){
	if(editMode){
		$(".editor").show();
		$(".viewer").hide();
	}
	else{			
		$(".editor").hide();
		$(".viewer").show();
	}
	//reactivate show+hide behaviour for balloon list
	$(".accordion").accordion("destroy").accordion({
		collapsible:true, 
		autoHeight:false,
		header:"h3"
	});
};

function doLayout(){		
	var animationOptions = {duration:500, step:refreshAccordions, complete:refreshAccordions} ;
	var showOptions = {height:"show", opacity:1.0};
	var hideOptions = {height:"hide", opacity:0};
	if(editMode){
		$(".editor").animate( showOptions, animationOptions);
		$(".viewer").animate( hideOptions, animationOptions);
	}
	else{			
		$(".editor").animate( hideOptions, animationOptions);
		$(".viewer").animate( showOptions, animationOptions);
	}
};

function setEditMode(mode){
	editMode = mode;
	doLayout();
	return editMode;
};

function toggleEditMode(){
	return setEditMode(!editMode);
};

$(function(){

	//configure editmode for startup
	setEditMode(false);
	
	//configure buttons in UI
	$(".button.editmode", ".button.addballoon", ".button.refresh").button();

	//bind edit button
	$(".button.editmode").click(function(){
		if(toggleEditMode()){
			$(this).find("span").removeClass("ui-icon-wrench");
			$(this).find("span").addClass("ui-icon-circle-triangle-e");
		}
		else{
			$(this).find("span").removeClass("ui-icon-circle-triangle-e");			
			$(this).find("span").addClass("ui-icon-wrench");
		}
	});

	//create balloons this way...
	$(".button.addballoon").click(function(){
		sendToJava("addBalloon");
	});
	
	$(".button.refresh").click(function(){
		resetLayout();
	});

	//configure top level nav
		
	//make the tabs like tabs
	$(".tabs").tabs({
		show:function(event, ui){
			resetLayout();
		}
	});
	
	//make graph and viewer dynamic (balloons will be made dynamic when they appear)
	$("#graphtab, #viewertab").each(function(){
		addDynamism(this);
	});

	//ask java to run initial config (and send any data)
	sendToJava("onload");

});