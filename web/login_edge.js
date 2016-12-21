/**
 * Adobe Edge: symbol definitions
 */
(function($, Edge, compId){
//images folder
var im='images/';

var fonts = {};


var resources = [
];
var symbols = {
"stage": {
   version: "1.5.0",
   minimumCompatibleVersion: "1.5.0",
   build: "1.5.0.217",
   baseState: "Base State",
   initialState: "Base State",
   gpuAccelerate: false,
   resizeInstances: false,
   content: {
         dom: [
         {
            id:'Image',
            type:'image',
            rect:['199','604','1920px','1024px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"dabingjing.png",'0px','0px']
         },
         {
            id:'luxing',
            type:'image',
            rect:['90','159','688px','281px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"luxing.png",'0px','0px']
         },
         {
            id:'luxing2',
            type:'image',
            rect:['90','159','688px','281px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"luxing.png",'0px','0px']
         },
         {
            id:'luxing3',
            type:'image',
            rect:['90','159','688px','281px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"luxing.png",'0px','0px']
         },
         {
            id:'luxing3Copy',
            type:'image',
            rect:['90','159','688px','281px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"luxing.png",'0px','0px']
         },
         {
            id:'liouxing2',
            type:'image',
            rect:['1314px','-11px','500px','500px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"116562012030813235815.png",'0px','0px'],
            transform:[[],['7'],[],['0.237','0.237']]
         },
         {
            id:'liouxing2Copy',
            type:'image',
            rect:['1314px','-11px','500px','500px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"116562012030813235815.png",'0px','0px'],
            transform:[[],['7'],[],['0.237','0.237']]
         },
         {
            id:'liouxing2Copy2',
            type:'image',
            rect:['1314px','-11px','500px','500px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"116562012030813235815.png",'0px','0px'],
            transform:[[],['7'],[],['0.237','0.237']]
         },
         {
            id:'Image2',
            type:'image',
            rect:['-120px','-28px','1920px','1024px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"shu.png",'0px','0px']
         },
         {
            id:'shanguang',
            type:'image',
            rect:['-9px','-12px','1920px','1024px','auto','auto'],
            fill:["rgba(0,0,0,0)",im+"shanguang.png",'0px','0px']
         }],
         symbolInstances: [

         ]
      },
   states: {
      "Base State": {
         "${_luxing2}": [
            ["style", "top", '-170px'],
            ["transform", "rotateZ", '-11deg'],
            ["style", "opacity", '0'],
            ["style", "left", '1596px'],
            ["style", "-webkit-transform-origin", [49.38,52.67], {valueTemplate:'@@0@@% @@1@@%'} ],
            ["style", "-moz-transform-origin", [49.38,52.67],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "-ms-transform-origin", [49.38,52.67],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "msTransformOrigin", [49.38,52.67],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "-o-transform-origin", [49.38,52.67],{valueTemplate:'@@0@@% @@1@@%'}]
         ],
         "${_Image2}": [
            ["style", "top", '4px'],
            ["style", "left", '0px']
         ],
         "${_luxing3Copy}": [
            ["style", "top", '380px'],
            ["transform", "rotateZ", '-11deg'],
            ["style", "opacity", '0'],
            ["style", "left", '1221px'],
            ["style", "-webkit-transform-origin", [97.47,-556.34], {valueTemplate:'@@0@@% @@1@@%'} ],
            ["style", "-moz-transform-origin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "-ms-transform-origin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "msTransformOrigin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "-o-transform-origin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}]
         ],
         "${_shanguang}": [
            ["style", "top", '4px'],
            ["style", "opacity", '1'],
            ["style", "left", '0px']
         ],
         "${_liouxing2Copy}": [
            ["style", "top", '-295px'],
            ["transform", "scaleY", '0.23723'],
            ["transform", "rotateZ", '18deg'],
            ["transform", "scaleX", '0.23723'],
            ["style", "left", '1604px']
         ],
         "${_liouxing2Copy2}": [
            ["style", "top", '-295px'],
            ["transform", "scaleY", '0.23723'],
            ["transform", "rotateZ", '18deg'],
            ["transform", "scaleX", '0.23723'],
            ["style", "left", '1604px']
         ],
         "${_luxing3}": [
            ["style", "top", '-292px'],
            ["transform", "rotateZ", '-11deg'],
            ["style", "opacity", '0'],
            ["style", "left", '1263px'],
            ["style", "-webkit-transform-origin", [97.47,-556.34], {valueTemplate:'@@0@@% @@1@@%'} ],
            ["style", "-moz-transform-origin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "-ms-transform-origin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "msTransformOrigin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}],
            ["style", "-o-transform-origin", [97.47,-556.34],{valueTemplate:'@@0@@% @@1@@%'}]
         ],
         "${_luxing}": [
            ["style", "top", '-171px'],
            ["style", "left", '1333px'],
            ["transform", "rotateZ", '-11deg']
         ],
         "${_Stage}": [
            ["color", "background-color", 'rgba(255,255,255,1)'],
            ["style", "overflow", 'hidden'],
            ["style", "height", '1000px'],
            ["style", "width", '1800px']
         ],
         "${_Image}": [
            ["style", "left", '-1px'],
            ["style", "top", '0px']
         ],
         "${_liouxing2}": [
            ["style", "top", '-295px'],
            ["transform", "scaleY", '0.23723'],
            ["transform", "rotateZ", '18deg'],
            ["transform", "scaleX", '0.23723'],
            ["style", "left", '1604px']
         ]
      }
   },
   timelines: {
      "Default Timeline": {
         fromState: "Base State",
         toState: "",
         duration: 10250,
         autoPlay: true,
         timeline: [
            { id: "eid18", tween: [ "style", "${_luxing}", "left", '-327px', { fromValue: '1333px'}], position: 0, duration: 1000, easing: "easeInQuad" },
            { id: "eid172", tween: [ "style", "${_luxing3Copy}", "opacity", '0', { fromValue: '0'}], position: 2047, duration: 0, easing: "easeInQuad" },
            { id: "eid173", tween: [ "style", "${_luxing3Copy}", "opacity", '1', { fromValue: '0'}], position: 2994, duration: 106, easing: "easeInQuad" },
            { id: "eid265", tween: [ "style", "${_luxing3Copy}", "opacity", '0', { fromValue: '1'}], position: 6664, duration: 0, easing: "easeInQuad" },
            { id: "eid266", tween: [ "style", "${_luxing3Copy}", "opacity", '1', { fromValue: '0'}], position: 7611, duration: 250, easing: "easeInQuad" },
            { id: "eid267", tween: [ "style", "${_luxing3Copy}", "opacity", '1', { fromValue: '0'}], position: 7861, duration: 53, easing: "easeInQuad" },
            { id: "eid257", tween: [ "style", "${_luxing3Copy}", "opacity", '1', { fromValue: '0'}], position: 8558, duration: 106, easing: "easeInQuad" },
            { id: "eid246", tween: [ "style", "${_luxing3Copy}", "opacity", '0', { fromValue: '1'}], position: 8711, duration: 0, easing: "easeInQuad" },
            { id: "eid247", tween: [ "style", "${_luxing3Copy}", "opacity", '1', { fromValue: '0'}], position: 9658, duration: 60, easing: "easeInQuad" },
            { id: "eid42", tween: [ "style", "${_Image2}", "top", '-21px', { fromValue: '4px'}], position: 0, duration: 5000, easing: "easeInQuad" },
            { id: "eid202", tween: [ "style", "${_Image2}", "top", '4px', { fromValue: '-21px'}], position: 5000, duration: 5250, easing: "easeInQuad" },
            { id: "eid174", tween: [ "style", "${_luxing3Copy}", "left", '166px', { fromValue: '1221px'}], position: 2994, duration: 1036, easing: "easeInQuad" },
            { id: "eid175", tween: [ "style", "${_luxing3Copy}", "left", '-327px', { fromValue: '166px'}], position: 4030, duration: 17, easing: "easeInQuad" },
            { id: "eid273", tween: [ "style", "${_luxing3Copy}", "left", '-327px', { fromValue: '1333px'}], position: 6664, duration: 1000, easing: "easeInQuad" },
            { id: "eid268", tween: [ "style", "${_luxing3Copy}", "left", '-327px', { fromValue: '1596px'}], position: 7664, duration: 947, easing: "easeInQuad" },
            { id: "eid269", tween: [ "style", "${_luxing3Copy}", "left", '-327px', { fromValue: '1596px'}], position: 8611, duration: 53, easing: "easeInQuad" },
            { id: "eid258", tween: [ "style", "${_luxing3Copy}", "left", '-358px', { fromValue: '1263px'}], position: 8664, duration: 930, easing: "easeInQuad" },
            { id: "eid259", tween: [ "style", "${_luxing3Copy}", "left", '-327px', { fromValue: '-358px'}], position: 9594, duration: 17, easing: "easeInQuad" },
            { id: "eid248", tween: [ "style", "${_luxing3Copy}", "left", '166px', { fromValue: '1221px'}], position: 9658, duration: 582, easing: "easeInQuad" },
            { id: "eid249", tween: [ "style", "${_luxing3Copy}", "left", '-327px', { fromValue: '166px'}], position: 10240, duration: 10, easing: "easeInQuad" },
            { id: "eid88", tween: [ "style", "${_luxing2}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid587", tween: [ "style", "${_luxing2}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid588", tween: [ "style", "${_luxing2}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid589", tween: [ "style", "${_luxing2}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid590", tween: [ "style", "${_luxing2}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid83", tween: [ "style", "${_luxing2}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 1000, duration: 0, easing: "easeInQuad" },
            { id: "eid591", tween: [ "style", "${_luxing2}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 1000, duration: 0, easing: "easeInQuad" },
            { id: "eid592", tween: [ "style", "${_luxing2}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 1000, duration: 0, easing: "easeInQuad" },
            { id: "eid593", tween: [ "style", "${_luxing2}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 1000, duration: 0, easing: "easeInQuad" },
            { id: "eid594", tween: [ "style", "${_luxing2}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 1000, duration: 0, easing: "easeInQuad" },
            { id: "eid297", tween: [ "style", "${_liouxing2Copy2}", "left", '-358px', { fromValue: '1604px'}], position: 5500, duration: 2077, easing: "easeInQuad" },
            { id: "eid285", tween: [ "style", "${_liouxing2Copy2}", "left", '-358px', { fromValue: '1604px'}], position: 7577, duration: 1923, easing: "easeInQuad" },
            { id: "eid125", tween: [ "style", "${_luxing3}", "opacity", '0', { fromValue: '0'}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid126", tween: [ "style", "${_luxing3}", "opacity", '1', { fromValue: '0'}], position: 1894, duration: 106, easing: "easeInQuad" },
            { id: "eid36", tween: [ "style", "${_Image}", "left", '-120px', { fromValue: '-1px'}], position: 0, duration: 5250, easing: "easeInQuad" },
            { id: "eid187", tween: [ "style", "${_Image}", "left", '0px', { fromValue: '-120px'}], position: 5250, duration: 5000, easing: "easeInQuad" },
            { id: "eid130", tween: [ "style", "${_luxing3}", "top", '753px', { fromValue: '-292px'}], position: 1894, duration: 1036, easing: "easeInQuad" },
            { id: "eid131", tween: [ "style", "${_luxing3}", "top", '957px', { fromValue: '753px'}], position: 2930, duration: 17, easing: "easeInQuad" },
            { id: "eid43", tween: [ "style", "${_Image2}", "left", '-119px', { fromValue: '0px'}], position: 0, duration: 5000, easing: "easeInQuad" },
            { id: "eid201", tween: [ "style", "${_Image2}", "left", '0px', { fromValue: '-119px'}], position: 5000, duration: 5250, easing: "easeInQuad" },
            { id: "eid282", tween: [ "style", "${_liouxing2Copy}", "left", '-358px', { fromValue: '1604px'}], position: 3549, duration: 1951, easing: "easeInQuad" },
            { id: "eid81", tween: [ "style", "${_luxing2}", "opacity", '0', { fromValue: '0'}], position: 0, duration: 0, easing: "easeInQuad" },
            { id: "eid86", tween: [ "style", "${_luxing2}", "opacity", '1', { fromValue: '0'}], position: 947, duration: 250, easing: "easeInQuad" },
            { id: "eid80", tween: [ "style", "${_luxing2}", "opacity", '1', { fromValue: '0'}], position: 1197, duration: 53, easing: "easeInQuad" },
            { id: "eid170", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 2994, duration: 0, easing: "easeInQuad" },
            { id: "eid171", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 3047, duration: 0, easing: "easeInQuad" },
            { id: "eid274", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 6664, duration: 0, easing: "easeInOutQuad" },
            { id: "eid263", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 7611, duration: 0, easing: "easeInQuad" },
            { id: "eid264", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 7664, duration: 0, easing: "easeInQuad" },
            { id: "eid254", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 8558, duration: 0, easing: "easeInQuad" },
            { id: "eid255", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 8611, duration: 0, easing: "easeInQuad" },
            { id: "eid244", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 9658, duration: 0, easing: "easeInQuad" },
            { id: "eid245", tween: [ "transform", "${_luxing3Copy}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 9688, duration: 0, easing: "easeInQuad" },
            { id: "eid276", tween: [ "style", "${_liouxing2}", "top", '750px', { fromValue: '-295px'}], position: 0, duration: 2000, easing: "easeInQuad" },
            { id: "eid275", tween: [ "style", "${_liouxing2}", "left", '-358px', { fromValue: '1604px'}], position: 0, duration: 2000, easing: "easeInQuad" },
            { id: "eid87", tween: [ "style", "${_luxing2}", "left", '-327px', { fromValue: '1596px'}], position: 947, duration: 1000, easing: "easeInQuad" },
            { id: "eid62", tween: [ "style", "${_luxing2}", "left", '-327px', { fromValue: '1596px'}], position: 1947, duration: 53, easing: "easeInQuad" },
            { id: "eid168", tween: [ "style", "${_luxing3Copy}", "top", '1076px', { fromValue: '380px'}], position: 2994, duration: 1036, easing: "easeInQuad" },
            { id: "eid169", tween: [ "style", "${_luxing3Copy}", "top", '957px', { fromValue: '1076px'}], position: 4030, duration: 17, easing: "easeInQuad" },
            { id: "eid272", tween: [ "style", "${_luxing3Copy}", "top", '957px', { fromValue: '19px'}], position: 6664, duration: 1000, easing: "easeInQuad" },
            { id: "eid270", tween: [ "style", "${_luxing3Copy}", "top", '957px', { fromValue: '-170px'}], position: 7664, duration: 947, easing: "easeInQuad" },
            { id: "eid271", tween: [ "style", "${_luxing3Copy}", "top", '957px', { fromValue: '-170px'}], position: 8611, duration: 53, easing: "easeInQuad" },
            { id: "eid252", tween: [ "style", "${_luxing3Copy}", "top", '753px', { fromValue: '-292px'}], position: 8664, duration: 930, easing: "easeInQuad" },
            { id: "eid253", tween: [ "style", "${_luxing3Copy}", "top", '957px', { fromValue: '753px'}], position: 9594, duration: 17, easing: "easeInQuad" },
            { id: "eid250", tween: [ "style", "${_luxing3Copy}", "top", '1076px', { fromValue: '380px'}], position: 9658, duration: 582, easing: "easeInQuad" },
            { id: "eid251", tween: [ "style", "${_luxing3Copy}", "top", '957px', { fromValue: '1076px'}], position: 10240, duration: 10, easing: "easeInQuad" },
            { id: "eid84", tween: [ "style", "${_luxing2}", "top", '957px', { fromValue: '-170px'}], position: 947, duration: 1000, easing: "easeInQuad" },
            { id: "eid61", tween: [ "style", "${_luxing2}", "top", '957px', { fromValue: '-170px'}], position: 1947, duration: 53, easing: "easeInQuad" },
            { id: "eid45", tween: [ "style", "${_shanguang}", "top", '-21px', { fromValue: '4px'}], position: 0, duration: 5000, easing: "easeInQuad" },
            { id: "eid192", tween: [ "style", "${_shanguang}", "top", '4px', { fromValue: '-21px'}], position: 5000, duration: 5250, easing: "easeInQuad" },
            { id: "eid296", tween: [ "style", "${_liouxing2Copy2}", "top", '750px', { fromValue: '-295px'}], position: 5500, duration: 2077, easing: "easeInQuad" },
            { id: "eid284", tween: [ "style", "${_liouxing2Copy2}", "top", '750px', { fromValue: '-295px'}], position: 7577, duration: 1923, easing: "easeInQuad" },
            { id: "eid37", tween: [ "style", "${_Image}", "top", '-24px', { fromValue: '0px'}], position: 0, duration: 5250, easing: "easeInQuad" },
            { id: "eid188", tween: [ "style", "${_Image}", "top", '0px', { fromValue: '-24px'}], position: 5250, duration: 5000, easing: "easeInQuad" },
            { id: "eid49", tween: [ "style", "${_shanguang}", "opacity", '0', { fromValue: '1'}], position: 0, duration: 1000, easing: "easeInQuad" },
            { id: "eid50", tween: [ "style", "${_shanguang}", "opacity", '0.7', { fromValue: '0'}], position: 1000, duration: 750, easing: "easeInQuad" },
            { id: "eid51", tween: [ "style", "${_shanguang}", "opacity", '0', { fromValue: '0.7'}], position: 1750, duration: 1250, easing: "easeInQuad" },
            { id: "eid56", tween: [ "style", "${_shanguang}", "opacity", '0.8', { fromValue: '0'}], position: 3000, duration: 750, easing: "easeInQuad" },
            { id: "eid57", tween: [ "style", "${_shanguang}", "opacity", '0.2', { fromValue: '0.8'}], position: 3750, duration: 500, easing: "easeInQuad" },
            { id: "eid193", tween: [ "style", "${_shanguang}", "opacity", '0', { fromValue: '1'}], position: 5000, duration: 1000, easing: "easeInQuad" },
            { id: "eid194", tween: [ "style", "${_shanguang}", "opacity", '0.7', { fromValue: '0'}], position: 6000, duration: 750, easing: "easeInQuad" },
            { id: "eid195", tween: [ "style", "${_shanguang}", "opacity", '0', { fromValue: '0.7'}], position: 6750, duration: 1250, easing: "easeInQuad" },
            { id: "eid196", tween: [ "style", "${_shanguang}", "opacity", '0.8', { fromValue: '0'}], position: 8000, duration: 750, easing: "easeInQuad" },
            { id: "eid197", tween: [ "style", "${_shanguang}", "opacity", '0.2', { fromValue: '0.8'}], position: 8750, duration: 750, easing: "easeInQuad" },
            { id: "eid198", tween: [ "style", "${_shanguang}", "opacity", '1', { fromValue: '0.2'}], position: 9500, duration: 500, easing: "easeInQuad" },
            { id: "eid1", tween: [ "gradient", "${_Stage}", "background-image", [270,[['rgba(255,255,255,0.00)',0],['rgba(255,255,255,0.00)',100]]], { fromValue: [270,[['rgba(255,255,255,0.00)',0],['rgba(255,255,255,0.00)',100]]]}], position: 0, duration: 0 },
            { id: "eid44", tween: [ "style", "${_shanguang}", "left", '-119px', { fromValue: '0px'}], position: 0, duration: 5000, easing: "easeInQuad" },
            { id: "eid191", tween: [ "style", "${_shanguang}", "left", '0px', { fromValue: '-119px'}], position: 5000, duration: 5250, easing: "easeInQuad" },
            { id: "eid20", tween: [ "style", "${_luxing}", "top", '767px', { fromValue: '-171px'}], position: 0, duration: 1000, easing: "easeInQuad" },
            { id: "eid128", tween: [ "style", "${_luxing3}", "left", '-358px', { fromValue: '1263px'}], position: 1894, duration: 1036, easing: "easeInQuad" },
            { id: "eid129", tween: [ "style", "${_luxing3}", "left", '-327px', { fromValue: '-358px'}], position: 2930, duration: 17, easing: "easeInQuad" },
            { id: "eid176", tween: [ "style", "${_luxing3Copy}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 2994, duration: 53, easing: "easeInQuad" },
            { id: "eid595", tween: [ "style", "${_luxing3Copy}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 2994, duration: 53, easing: "easeInQuad" },
            { id: "eid596", tween: [ "style", "${_luxing3Copy}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 2994, duration: 53, easing: "easeInQuad" },
            { id: "eid597", tween: [ "style", "${_luxing3Copy}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 2994, duration: 53, easing: "easeInQuad" },
            { id: "eid598", tween: [ "style", "${_luxing3Copy}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 2994, duration: 53, easing: "easeInQuad" },
            { id: "eid261", tween: [ "style", "${_luxing3Copy}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7611, duration: 0, easing: "easeInQuad" },
            { id: "eid599", tween: [ "style", "${_luxing3Copy}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7611, duration: 0, easing: "easeInQuad" },
            { id: "eid600", tween: [ "style", "${_luxing3Copy}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7611, duration: 0, easing: "easeInQuad" },
            { id: "eid601", tween: [ "style", "${_luxing3Copy}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7611, duration: 0, easing: "easeInQuad" },
            { id: "eid602", tween: [ "style", "${_luxing3Copy}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7611, duration: 0, easing: "easeInQuad" },
            { id: "eid262", tween: [ "style", "${_luxing3Copy}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7664, duration: 0, easing: "easeInQuad" },
            { id: "eid603", tween: [ "style", "${_luxing3Copy}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7664, duration: 0, easing: "easeInQuad" },
            { id: "eid604", tween: [ "style", "${_luxing3Copy}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7664, duration: 0, easing: "easeInQuad" },
            { id: "eid605", tween: [ "style", "${_luxing3Copy}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7664, duration: 0, easing: "easeInQuad" },
            { id: "eid606", tween: [ "style", "${_luxing3Copy}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [49.38,52.67]}], position: 7664, duration: 0, easing: "easeInQuad" },
            { id: "eid260", tween: [ "style", "${_luxing3Copy}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 8558, duration: 53, easing: "easeInQuad" },
            { id: "eid607", tween: [ "style", "${_luxing3Copy}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 8558, duration: 53, easing: "easeInQuad" },
            { id: "eid608", tween: [ "style", "${_luxing3Copy}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 8558, duration: 53, easing: "easeInQuad" },
            { id: "eid609", tween: [ "style", "${_luxing3Copy}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 8558, duration: 53, easing: "easeInQuad" },
            { id: "eid610", tween: [ "style", "${_luxing3Copy}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 8558, duration: 53, easing: "easeInQuad" },
            { id: "eid243", tween: [ "style", "${_luxing3Copy}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 9658, duration: 30, easing: "easeInQuad" },
            { id: "eid611", tween: [ "style", "${_luxing3Copy}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 9658, duration: 30, easing: "easeInQuad" },
            { id: "eid612", tween: [ "style", "${_luxing3Copy}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 9658, duration: 30, easing: "easeInQuad" },
            { id: "eid613", tween: [ "style", "${_luxing3Copy}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 9658, duration: 30, easing: "easeInQuad" },
            { id: "eid614", tween: [ "style", "${_luxing3Copy}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 9658, duration: 30, easing: "easeInQuad" },
            { id: "eid281", tween: [ "style", "${_liouxing2Copy}", "top", '750px', { fromValue: '-295px'}], position: 3549, duration: 1951, easing: "easeInQuad" },
            { id: "eid298", tween: [ "transform", "${_liouxing2Copy2}", "rotateZ", '18deg', { fromValue: '18deg'}], position: 5500, duration: 0, easing: "easeInQuad" },
            { id: "eid286", tween: [ "transform", "${_liouxing2Copy2}", "rotateZ", '18deg', { fromValue: '18deg'}], position: 7423, duration: 0, easing: "easeInQuad" },
            { id: "eid283", tween: [ "transform", "${_liouxing2Copy}", "rotateZ", '18deg', { fromValue: '18deg'}], position: 3549, duration: 0, easing: "easeInQuad" },
            { id: "eid31", tween: [ "transform", "${_luxing}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 0, duration: 0, easing: "easeInOutQuad" },
            { id: "eid277", tween: [ "transform", "${_liouxing2}", "rotateZ", '18deg', { fromValue: '18deg'}], position: 0, duration: 0, easing: "easeInQuad" },
            { id: "eid123", tween: [ "transform", "${_luxing3}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 1894, duration: 0, easing: "easeInQuad" },
            { id: "eid124", tween: [ "transform", "${_luxing3}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 1947, duration: 0, easing: "easeInQuad" },
            { id: "eid85", tween: [ "transform", "${_luxing2}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 947, duration: 0, easing: "easeInQuad" },
            { id: "eid76", tween: [ "transform", "${_luxing2}", "rotateZ", '-11deg', { fromValue: '-11deg'}], position: 1000, duration: 0, easing: "easeInQuad" },
            { id: "eid132", tween: [ "style", "${_luxing3}", "-webkit-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 1894, duration: 53, easing: "easeInQuad" },
            { id: "eid615", tween: [ "style", "${_luxing3}", "-moz-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 1894, duration: 53, easing: "easeInQuad" },
            { id: "eid616", tween: [ "style", "${_luxing3}", "-ms-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 1894, duration: 53, easing: "easeInQuad" },
            { id: "eid617", tween: [ "style", "${_luxing3}", "msTransformOrigin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 1894, duration: 53, easing: "easeInQuad" },
            { id: "eid618", tween: [ "style", "${_luxing3}", "-o-transform-origin", [49.38,52.67], { valueTemplate: '@@0@@% @@1@@%', fromValue: [97.47,-556.34]}], position: 1894, duration: 53, easing: "easeInQuad" }         ]
      }
   }
}
};


Edge.registerCompositionDefn(compId, symbols, fonts, resources);

/**
 * Adobe Edge DOM Ready Event Handler
 */
$(window).ready(function() {
     Edge.launchComposition(compId);
});
})(jQuery, AdobeEdge, "EDGE-30622614");
