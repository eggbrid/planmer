precision mediump float;

varying vec4 vSpecular;         //用于传递给片元着色器的镜面光分量
varying vec4 vDiffuse;          //用于传递给片元着色器的散射光分量
varying vec4 vAmbient;          //用于传递给片元着色器的环境光分量

void main()
{
    vec4 vFinalColor = vec4(1.0, 0.0, 1.0, 0.0);

    gl_FragColor = vFinalColor*vSpecular + vFinalColor*vDiffuse + vFinalColor*vAmbient;  //最终颜色
}