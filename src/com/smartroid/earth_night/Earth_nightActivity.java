package com.smartroid.earth_night;

import android.graphics.Bitmap;
import android.util.Log;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Sphere;
import min3d.vos.Light;
import min3d.vos.LightType;
import min3d.vos.TextureVo;

public class Earth_nightActivity extends RendererActivity {

	private final float DEGREE = (float) (Math.PI / 180);

	Object3dContainer _sphere;

	Light _lightGreen;
	Object3dContainer _boxGreen;

	int _count = 0;
	float angel = 0;

	public void initScene() {
		scene.camera().position.setAll(0, 2, -2.3f);
		scene.camera().position.rotateX(-50 * DEGREE);
		// Add three lights

		_lightGreen = new Light();
		_lightGreen.ambient.setAll(0x88001100);
		_lightGreen.diffuse.setAll(0xffffffff);
		_lightGreen.type(LightType.DIRECTIONAL);
		_lightGreen.position.setAll(-0.7f, 0, -1.9f);
		scene.lights().add(_lightGreen);

		_sphere = new Sphere(1.0f, 40, 40);
		_sphere.scale().multiply(1.5f);
		_sphere.vertexColorsEnabled(false);
		_sphere.rotation().x += 1f;
		_sphere.position().y = -0.7f;
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.earth);
		Shared.textureManager().addTextureId(b, "earth", true);
		b.recycle();

		TextureVo t = new TextureVo("earth");
		_sphere.textures().add(t);

		scene.addChild(_sphere);

		_boxGreen = new Box(0.07f, 0.07f, 0.07f);
		_boxGreen.normalsEnabled(false);
		_boxGreen.vertexColorsEnabled(false);
		_boxGreen.defaultColor().setAll(0xaa00ff00);
		scene.addChild(_boxGreen);

		_count = 0;
	}

	@Override
	public void updateScene() {

		float x = (float) (Math.sin(_count % 360 * DEGREE) * -1.5f);
		float z = (float) (Math.cos(_count % 360 * DEGREE) * 2.25f);
		// _lightGreen.position.setAll(x, 0, z);
		// _lightGreen.position.toNumber3d().z += 1;
		// Log.d("x , z ",x+" , "+z);
		//
		// angel += 0.01f;
		// angel %= 180f;
		_boxGreen.position().setAllFrom(_lightGreen.position.toNumber3d());

		_sphere.rotation().y += 0.1f;
		_count++;
	}
}