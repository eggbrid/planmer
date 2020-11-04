#version 300 es

precision highp float;
uniform sampler2D uTexture;
in vec2 vCoordinate;
in vec4 vSpecular;         //用于传递给片元着色器的镜面光分量
in vec4 vDiffuse;          //用于传递给片元着色器的散射光分量
in vec4 vAmbient;          //用于传递给片元着色器的环境光分量
out vec4 vFragColor;


void main(){

    vec4 color=texture(uTexture,vCoordinate);
    vFragColor=color*vSpecular + color*vDiffuse + color*vAmbient;  //最终颜色;
}
