# RayMarching
A 3D renderer made in Java that uses the [Ray Marching algorithm]

## Expandability
To implement a new shape, all you have to do is create a new class implementing the interface "Geometry" and search the [Distance estimation function](https://www.iquilezles.org/www/articles/distfunctions/distfunctions.htm)

## TODO
There is so much work to do:
<ul>
  <li>Lights don't interact properly with the objects.</li>
  <li>Right now it can only render in black and white.</li>
  <li>The render is fast (with low resolutions), but there is no implemented "repaint".</li>
  <li>Neither the camera nor the objects can be moved after executing.</li>
  <li>The scale of the objects and the distance/position has a weird scale and orientation.</li>
  <li>Lots of minor tweaks and improvements</li>
 </ul>

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
