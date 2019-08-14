# JPEG Encoder/Decoder
This project is created following the outline of Assignment 2 from CMPT 365 Multimedia Systems, with the goal of showing the effects of lossy compression. Lossy compression is a class of data encoding methods that estimates the original image instead of keeping an exact representation, therefore losing information in the process, hence the “lossy” part of the name. This project aims to emulate the effects of JPEG compression, a widely used lossy compression method using the specified quantization table DCT matrix along with 4:2:0 chromatic downsampling.

## Method

The project is coded in java, doing the color space conversion, downsampling, discrete cosine transformation, and quantization components of the JFIF format from the JPEG compression type, then decodes the same information in order to demonstrate the effects of said compression method on the visual qualities of a digital picture. The `java.awt.image.BufferedImage` and `javax.imageio.*` libraries are used to display the images in a simple GUI and a `Matrix` class from princeton.edu is used for calculations.

The color space used in this project is in the YUV basis, to convert to this from the regular RGB basis, one line equations were used instead of matrix multiplication to increase the readability of the code, equations used are as follows:
```
-       Y =  0.299R + 0.587G + 0.114B
-       U = -0.147R - 0.289G + 0.436B
-       V =  0.615R - 0.515G - 0.100B
```
The reasoning behind this conversion is that the human eye is more sensitive to some colors compared to others, which results the weighted distribution of each color in the RGB basis in the conversion. This new basis represents color in luminous a component via the “Y” axis and the chrominous components via the “U” and “V” component.

Downsampling is only applied to the chrominous portions of this YUV basis in a 4:2:0 scale. Which means that information in each of the UV axie is reduced to ¼ of their original size. This downsampling is the first part in the compression where information is actually lost, with the reasoning behind discarding this information being that people can’t see the chrominous part as well as the luminous parts. 

The discrete cosine transform portion of the project is done by extracting individual 8x8 pixel blocks from the original picture and multiplying the DCT II matrix to it as outlined in the lectures. The reasoning behind this part of the conversion is that this multiplication is actually recording the intensity of each of the 64 cosine waves, which in combination with the fact that higher frequencies represents a much lower fraction of the picture, creates a situation where the information is clustered heavily at the lower frequencies, which will be useful later on in the quantization step. It is also useful to note that a library provided by princeton.edu was used to perform the matrix multiplication in both this step and the reverse of this step to simplify the process.

As previously mentioned, the 8x8 information block is mostly weighted at the lower frequencies, with the higher frequencies often being an insignificant fraction to its counterpart. The quantization step allows for the removal of a significant portion of the higher frequency waves from the information block, which is done by dividing the entries in the 8x8 post DCT block by a corresponding entry in a predefined quantization table. This step is justified in the send that human recognition of certain patterns and patterns at higher frequencies is much worse than some others, therefore the quantization table entries are actually inverted weights of how sensitive the human eye is to that particular cosine wave pattern. Various ratios of the same quantization table was used. Ratios between zero and one resulted in more non-zero entries after the conversion, although the difference was not visibly distinguishable in the image at a reasonable zoom. While the higher ratios, such as 5x, resulted in a much more blocky image when compared to the original.

When reversing the effects of compression, we simply followed the formula outlined by the lectures for reversing the DCT, then multiplied the quantization matrix to obtain the Y, U, and V information.

## Conclusion

The effects of chromatic downsampling was visible before the application of DCT and quantization. The 4:2:2 downsampling had a clear advantage over 4:2:0, the lower chrominance resolution was clearly visible compared to the original image where the higher chrominance resolution difference was only barely detectable to the eye. The effects of DCT and quantization results in clear visible white dots on the final image. It is suspected to be due to errors in the programming portions of this project. We hope to fix this portion in the future but different quantization matrices yield no obvious 

### Created By:
Gurjas Rai, Zi Xiang Zhao, Neil Nitin Mukesh

### Screenshot
![Image of GUI](https://github.com/Gurjas604/Proj/blob/master/screen-shots/Screen%20Shot%202019-08-13%20at%209.27.17%20PM.png?raw=true)


