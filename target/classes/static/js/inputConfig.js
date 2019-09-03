/**
 * Created by LENOVO on 2018/10/31.
 */
(function (){
    //inpu便利
    var attrInner='';
    var inputDom=document.getElementsByTagName('input');
    var textTareaDom=document.getElementsByTagName('textarea');
    for(var i=0;i<inputDom.length;i++){
        //style
        // inputDom[i].style.width='186px';
        //事件
        var onfocusFunction=inputDom[i].getAttribute('onfocus');
        if(!onfocusFunction){
            inputDom[i].onfocus=function(){
                var placeHolderInner=this.getAttribute('placeholder');
                attrInner=placeHolderInner;
                this.setAttribute('placeholder','');
            }
            inputDom[i].onblur=function(){
                this.setAttribute('placeholder',attrInner);
            }
        }
    }
    for(var i=0;i<textTareaDom.length;i++){
        textTareaDom[i].onfocus=function(){
            var placeHolderInner=this.getAttribute('placeholder');
            attrInner=placeHolderInner;
            this.setAttribute('placeholder','');
        }
        textTareaDom[i].onblur=function(){
            this.setAttribute('placeholder',attrInner);
        }
    }

})();