$(".js-item-dropdown-custom").on('click', function(){
      var $this = $(this);
      var value = $this.text(); 

      var textandvalue = $(".text-select", $this.closest(".btn-group"));
      textandvalue.html("" + value + "");
      $this.closest(".btn-group").attr("value", value);

      $("button", $this.closest(".btn-group")).removeClass('error');
      $("button", $this.closest(".btn-group")).removeClass('success');

      $("button", $this.closest(".btn-group")).addClass('success');
    }); 