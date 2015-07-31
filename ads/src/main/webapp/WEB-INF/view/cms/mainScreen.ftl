
<!-- header -->
<#include "../include/header.ftl" >
<!-- header end-->
<!-- menu -->
<#include "menu.ftl" >
<!-- menu end-->
<!-- banner -->
<div id="banner">
  <ul class="slides_container">
    <li><a href="productList0d87.html?no=8" id=HBA><img src="static/images/banner/HBA.jpg" width="960" height="400" title="HBA Series"></a></li>
    <li><a href="productList775e.html?no=11" id=Lamination><img src="static/images/banner/LA.jpg" width="960" height="400" title="Lamination"></a></li>
    <li><a href="productList0b76.html?no=10" id=Cast><img src="static/images/banner/TDie.jpg" width="960" height="400" title="Cast Film Machine"></a></li>
    <li><a href="productList5969.html?no=1" id=ABI><img src="static/images/banner/ABI.jpg" width="960" height="400" title="ABI Series"></a></li>
    <li><a href="productList34b1.html?no=4" id=Sheet><img src="static/images/banner/SE.jpg" width="960" height="400" title="Sheet Making Machine"></a></li>
  </ul>
  <a href="#" class="prev"></a> <a href="#" class="next"></a>
</div>
<!-- banner end -->
<p>&nbsp;</p>
<!-- content -->
<div id="contentIndex">
<a href="worldwide.html" onClick="_gaq.push(['worldwide office']);"><img src="static/images/banner/worldwide_long.jpg"></a>
<p>&nbsp;</p>
  <!-- left -->
  <div id="left">
    <!-- indexLeft -->
    <div id="indexLeft">
      <dl class="mainBox">
        <dt>About FKI</dt>
        <dd class="comp"> <img src="static/images/companyIndex.jpg" width="270">
          <p>&nbsp;</p>
          <span class="bold">Fong Kee International Machinery Co., Ltd. (FKI)</span> is the largest and finest machinery manufacturer in Taiwan Plastic Extrusion Industry. Established in 1953 by founder C.C Wei and spread its business to over 100 countries.
			<p>&nbsp;</p>
          <p class="align_r">
            <input name="" type="button" class="btn" value="More" onclick="location='company.html'">
          </p>
        </dd>
      </dl>
    </div>
    <!-- indexLeft end-->
    <!-- indexRight -->
    <div id="indexRight">
      <dl class="mainBox">
        <dt>Latest News</dt>
        <dd class="Latestnews">
            <ul class="news">
		
              <li>
                <p> <a href="newsdetaila907.html?no=28">Come to visit us booth for Taipeiplas!!!</a>  <span>(2014/9/25)</span></p>
              </li>
		
              <li>
                <p> <a href="newsdetail1d70.html?no=23">PE/PP Tandem Type Extrusion Lamination Machine in MALAYSIA</a>  <span>(2014/4/30)</span></p>
              </li>
		
              <li>
                <p> <a href="newsdetailc040.html?no=19">TAITRA & Reporters visit FONG KEE!</a>  <span>(2014/3/26)</span></p>
              </li>
		
              <li>
                <p> <a href="newsdetail0d87.html?no=8">FKI E-catalog now online</a>  <span>(2013/10/4)</span></p>
              </li>
		
            </ul>
   
          <p>&nbsp;</p>
          <p class="align_r">
            <input name="" type="button" class="btn" value="More" onclick="location='news.html'">
          </p>
        </dd>
      </dl>
    </div>
    <!-- indexRight end-->
  </div>
  <!-- left end -->
  <!-- right -->
  <div id="right">
      <dl class="mainBox">
        <dt>Exhibition News</dt>
        <dd class="Exhibition">
            <ul class="news">
		
              <li>
                <p><a href="newsdetaild851.html?no=29">International Plastic Fair in Japan</a> <span>(2014/10/13)</span></p>
              </li>
		
              <li>
                <p><a href="newsdetail494a.html?no=26">Taipei International Plastics&Rubber Industry Show 2014</a> <span>(2014/5/30)</span></p>
              </li>
		
              <li>
                <p><a href="newsdetailb229.html?no=17">[02-05, JUNE. 2014] SMALL & MEDIUM PROJECTS FAIR 2014</a> <span>(2014/3/18)</span></p>
              </li>
		
              <li>
                <p><a href="newsdetail4ed1.html?no=15">[2014.April 23-26] Chinaplas 2014 in Shanghai</a> <span>(2014/3/4)</span></p>
              </li>

         </ul>
          <p>&nbsp;</p>
          <p class="align_r">
            <input name="" type="button" class="btn" value="More" onclick="location='newsExpo.html'">
          </p>
        </dd>
      </dl>
  </div>
  <!-- right end -->
  <p class="clear">&nbsp;</p>
</div>
<!-- content end -->
<!-- footer -->
<#include "../include/footer.ftl">

<!-- footer end -->
</body>
</html>
<script>
var rand_no = Math.floor(Math.random()*6+1);//避免零
$(function(){
	$('#banner').slides({
		start:rand_no, //隨機開始
		preload: true,
		preloadImage:'img/loading.gif',
		play: 4000,
		pause: 1000,
		hoverPause: true,
		generatePagination: true
	});
});
</script>