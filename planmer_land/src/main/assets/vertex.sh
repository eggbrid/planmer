uniform mat4 uMVPMatrix;        //总变换矩阵

uniform vec3 uCamera;           //摄像机位置
uniform vec3 uLightLocation;    //光源位置
attribute vec3 aPosition;       //顶点位置
attribute vec3 aNormal;         //顶点法向量

varying vec4 vSpecular;         //用于传递给片元着色器的镜面光分量
varying vec4 vDiffuse;          //用于传递给片元着色器的散射光分量
varying vec4 vAmbient;          //用于传递给片元着色器的环境光分量

void main()
{
    float shininess=20.0;               //粗糙度，越小越光滑
    vec4 initAmbient = vec4(0.2, 0.2, 0.2, 1.0);       //设置环境光强度
    vec4 initDiffuse = vec4(0.8, 0.8, 0.8, 1.0);       //设置散射光强度
    vec4 initSpecular = vec4(0.5, 0.5, 0.5, 1.0);      //设置镜面光强度

    vAmbient=initAmbient;

    gl_Position = uMVPMatrix * vec4(aPosition,1);

    //求出法向量
    vec3 normalTarget=aPosition+aNormal;
    vec3 newNormal=(vec4(normalTarget,1)).xyz-(vec4(aPosition,1)).xyz;
    newNormal=normalize(newNormal);                     //向量规格化

    //计算从表面点到摄像机的向量
    vec3 eye= uCamera-(vec4(aPosition,1)).xyz;
    eye=normalize(eye);

    //计算从表面点到光源位置的向量
    vec3 vp= normalize(uLightLocation-(vec4(aPosition,1)).xyz);
    vp=normalize(vp);

    //计算散射光
    float nDotViewPosition=max(0.0, dot(newNormal,vp));
    vDiffuse=initDiffuse*nDotViewPosition;

    //计算镜面光
    vec3 halfVector=normalize(vp+eye);  //求视线与光线的半向量
    float nDotViewHalfVector=dot(newNormal,halfVector);         //法线与半向量的点积
    float powerFactor=max(0.0,pow(nDotViewHalfVector,shininess));
    vSpecular=initSpecular*powerFactor;             //计算镜面光的最终强度
}