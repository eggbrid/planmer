#version 300 es
in vec4 aPosition;
in vec2 aCoordinate;
in vec3 aNormal;         //顶点法向量

//顶点着色器
uniform mat4 uProjMatrix;
uniform mat4 uViewMatrix;
uniform mat4 uModelMatrix;
uniform mat4 uRotateMatrix;
uniform vec3 uLightLocation;    //光源位置
//uniform vec3 uCamera;           //摄像机位置

out vec2 vCoordinate;
out vec4 vSpecular;         //用于传递给片元着色器的镜面光分量
out vec4 vDiffuse;          //用于传递给片元着色器的散射光分量
out vec4 vAmbient;          //用于传递给片元着色器的环境光分量
void main(){
    float shininess=20.0;               //粗糙度，越小越光滑
    vec4 initAmbient = vec4(0.1, 0.1, 0.1, 1.0);       //设置环境光强度
    vec4 initDiffuse = vec4(0.0, 0.0, 0.0, 1.0);       //设置散射光强度
    vec4 initSpecular = vec4(1.5, 1.5, 1.2, 1.0);      //设置镜面光强度
    gl_Position=uProjMatrix*uViewMatrix*uModelMatrix*aPosition;
    vCoordinate=aCoordinate;
    vAmbient=initAmbient;
    vec3 eye= vec3(10, 10, 10);
    eye=normalize(eye);

    //求出法向量
    vec3 normalTarget=aPosition.xyz+aNormal;
    vec3 newNormal=normalTarget.xyz-aPosition.xyz;
    newNormal=normalize(newNormal);                     //向量规格化

    //计算从表面点到光源位置的向量
    vec3 vp= normalize(uLightLocation-aPosition.xyz);
    vp=normalize(vp);

    //计算散射光
    float nDotViewPosition=max(0.0, dot(newNormal,vp));
    vDiffuse=initDiffuse*nDotViewPosition;

    //计算镜面光
    vec3 halfVector=normalize(vp+eye);  //求视线与光线的半向量
    float nDotViewHalfVector=dot(newNormal,halfVector);         //法线与半向量的点积
    float powerFactor=max(0.0,nDotViewHalfVector);
    vSpecular=initSpecular*powerFactor;             //计算镜面光的最终强度



}