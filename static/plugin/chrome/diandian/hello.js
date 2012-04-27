// var xhr = new XMLHttpRequest();
// xhr.open("GET", "http://www.fl360.net/text/request.ashx?dl=dl", false);
// xhr.onreadystatechange = function() {
//     if (xhr.readyState == 4) {
//         var A = xhr.responseText;
//         if (A == "") {
//             var B = document.createElement("div");
//             B.id = "flgmsgshow";
//             B.innerHTML = '<div class=fl360-login" style=" z-index:99999;font:14px/1.6em \u5fae\u8f6f\u96c5\u9ed1,\u5b8b\u4f53; color:#000; position:fixed; bottom:40px; right:10px; width:280px; padding:10px; background:#f5f5f5; border:5px solid #e5e5e5;"><div class="inner"><div class="text" style="margin-bottom:10px;"><p class="p" style="margin:0; text-indent:2em;">\u60a8\u597d\uff0c\u8fd4\u5229\u72d7\u68c0\u6d4b\u5230\u8fd4\u5229\u4fe1\u606f\uff0c\u4f46\u60a8\u672a\u767b\u5f55\u8fd4\u5229\u72d7\u3002<strong class="strong" style="color:#c00;">\u82e5\u60a8\u672a\u767b\u5f55\uff0c\u60a8\u5c06\u65e0\u6cd5\u83b7\u53d6\u8fd4\u5229</strong>\u767b\u5f55\u540e\u60a8\u5c31\u53ef\u4ee5\u8f7b\u677e\u83b7\u53d6\u8fd4\u5229\uff01</p></div><div class="bt" style="text-align:center;"><a href="http://www.fl360.net/user/main.aspx" class="a" target="fl360" style="padding:5px 10px; background:#008000; color:#fff; text-decoration:none; border:1px solid #fff; border-right:1px solid #000; border-bottom:1px solid #000;">\u70b9\u6b64\u7acb\u5373\u767b\u5f55\u8fd4\u5229\u72d7</a></div></div></div>';
//             document.body.insertBefore(B, null)
//         }
//     }
// };
// xhr.send();
//console.log("222222");
// console.log(document.getElementsByClassName("user-nick")[0]);
// chrome.extension.sendRequest({greeting: "hello"}, function(response) {
//   console.log(response.farewell);
// }); 
// chrome.extension.onRequest.addListener(
//   function(request, sender, sendResponse) {
//   	console.log("ok")
//     console.log(sender.tab ?
//                 "from a content script:" + sender.tab.url :
//                 "from the extension");
//     if (request.greeting == "hello")
//       sendResponse({farewell: "goodbye"});
//     else
//       sendResponse({}); // snub them.
//   });