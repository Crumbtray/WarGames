package com.wargames.echoclient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EchoGame implements ApplicationListener {
	private SpriteBatch batch;
	private Skin skin;
	private Stage stage;
	private Socket socket;

	private final String Hostname = "localhost";
	private final int Port = 5000;

	private int messageCount = 0;
	
	@Override
	public void create() {        
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();

		try {
			socket = new Socket(Hostname, Port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final TextButton button = new TextButton("Click me", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);
		button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);

		button.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				try {
					
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

					BufferedReader in = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					
					out.println(messageCount);
					messageCount++;
					System.out.println("Button clicked, response: " + in.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		stage.addActor(button);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		batch.dispose();
	}

	@Override
	public void render() {        
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
