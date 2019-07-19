package eu.unareil.progAleatoire;

import java.util.ArrayList;
import java.util.List;

import eu.unareil.progAleatoire.bll.BLLException;
import eu.unareil.progAleatoire.bll.ProgAleatoire;
import eu.unareil.progAleatoire.bo.Stagiaire;
import eu.unareil.progAleatoire.dal.DAOFactory;
import eu.unareil.progAleatoire.dal.xmlImpl.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	private boolean lancer=false;
	private List <Text> lesNoms=new ArrayList<>();
	private List <CheckBox> cbNoms=new ArrayList<>();
	private List <HBox> hbNom = new ArrayList<>();
	private ProgAleatoire pr;
	private Stagiaire stTemp;
	private Text compt;
	//private Text gagnant,gagnantNomPrenom, gagnantInfos;
	private CheckBox cbCandidat;
	private Label lid,lnom,lprenom;
	private TextField tid,tnom,tprenom;
	private Button bsuiv,bprec,benreg,bsup,bnouv;
	private int index;
	private static final int NB_MAX_STAGIAIRES=15;
	private boolean nouveau;
    @SuppressWarnings("exports")
	@Override
    public void start(Stage fen) {
    	pr = ProgAleatoire.getInstance();
        try {
			pr.chargeXML();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attention");
			alert.setHeaderText("Le fichier n'existe pas...");
			alert.setContentText("Il vient d'être créé \n à l'URL:"+DAOFactory.getURL());	
			alert.showAndWait();
		}
        if (pr.afficheTous().isEmpty())
		{
			initSceneGestionStagiaire(fen);
			initFonct(fen);
		}
		else
		{
			initSceneProgAlea(fen);
		}
        fen.setTitle("Programme Aléatoire");
        fen.show();

        
	

    }
	private void initSceneProgAlea(Stage fen) {
		// TODO Auto-generated method stub
		compt=new Text();
		cbCandidat = null;
		compt.setFont(Font.font ("Verdana", 20));
		GridPane.setColumnIndex(compt, 1);
		GridPane.setRowIndex(compt, 3);
		Text lab = null;
		
		HBox hbox =null;
		int j=0;
		cbNoms.clear();
		lesNoms.clear();
		hbNom.clear();
		for(Stagiaire st : pr.afficheTous()) {
			cbCandidat = new CheckBox();
			lab = new Text(st.getPrenom()+" "+st.getNom());
			lab.setFont(Font.font ("Verdana", 20));
			lesNoms.add(lab);
			cbNoms.add(cbCandidat);	
			hbox = new HBox(2);
			cbNoms.get(j).setSelected(st.isJoue());
			cbNoms.get(j).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
										
					for (int k=0; k<cbNoms.size();k++) {
						if (cbNoms.get(k)==arg0.getSource())
						{
							if (cbNoms.get(k).isSelected())
							{
								pr.affiche(k).setJoue(true);					
							}
							else
							{
								pr.affiche(k).setJoue(false);
							}
							try {
								pr.modifieStagiaire(pr.affiche(k));
							} catch (BLLException e) {
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Une erreur s'est produite");
								alert.setHeaderText(e.getMessage());
								alert.showAndWait();		
							}
						}
					}
				}
				
			});
			hbox.getChildren().addAll(cbNoms.get(j), lesNoms.get(j));
			hbNom.add(hbox);
			j++;
			
		}
		int i=0;
		GridPane root = null;
		root=new GridPane();
		root.getChildren().add(getMenu(fen));
		for (i=0;i<lesNoms.size();i++)
		{
			GridPane.setColumnIndex(hbNom.get(i), 0);
			GridPane.setRowIndex(hbNom.get(i), i+1);
			root.getChildren().add(hbNom.get(i));
		}
		root.getChildren().add(compt);
		Label sp= new Label("");
		sp.setFont(Font.font ("Arial", 20));
		GridPane.setColumnIndex(sp, 0);
		GridPane.setRowIndex(sp, lesNoms.size());
		root.getChildren().add(sp);
		Label nb= new Label("Nombre de tour :");
		nb.setFont(Font.font ("Arial", 20));
		GridPane.setColumnIndex(nb, 0);
		GridPane.setRowIndex(nb, lesNoms.size()+1);
		GridPane.setHalignment(nb, HPos.RIGHT);
		TextField tnb= new TextField();
		GridPane.setColumnIndex(tnb, 1);
		
		GridPane.setRowIndex(tnb, lesNoms.size()+1);
		Button b = new Button("Lancer");
		b.setFont(Font.font ("Arial", 20));
		GridPane.setColumnIndex(b, 0);
		GridPane.setRowIndex(b, lesNoms.size()+2);
		root.getChildren().add(b);
		GridPane.setHalignment(b, HPos.RIGHT);
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (lancer==false)
				{
					lancer=true;
				
				Service<Void> lanceProgAlea = new Service<Void>(){

					  @Override
					  protected Task<Void> createTask() {
					    return new Task<Void>(){

					     @Override
					     protected Void call() throws Exception {
					    		int ii=0;
					        	pr.reset();
					    		for(Stagiaire st : pr.afficheTous()) {
					    			lesNoms.get(ii).setText(st.getPrenom()+" "+st.getNom());
					    			ii++;
					    		}
					        	int nbreTour;
								 nbreTour= Integer.parseInt(tnb.getText());
								
								  for (int i=1;i<=nbreTour;i++)
								  {
									  stTemp=null;
									  stTemp=pr.selectionHasard(i);
									lesNoms.get(pr.afficheTous().lastIndexOf(stTemp)).setFill(javafx.scene.paint.Color.RED);
									lesNoms.get(pr.afficheTous().lastIndexOf(stTemp)).setText(stTemp.getPrenom()+" "+stTemp.getNom()+" "+stTemp.getScore());
									compt.setText("Tour n° "+String.valueOf(i));
									try {
										Thread.sleep(500);
										lesNoms.get(pr.afficheTous().lastIndexOf(stTemp)).setFill(javafx.scene.paint.Color.BLACK);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								  }
								  Stagiaire st;
							      st =pr.gagnant();
							      Platform.runLater(() -> {
							    	  Alert alertG = new Alert(AlertType.INFORMATION);
										alertG.setTitle("Personne sélectionnée:");
										alertG.setHeaderText(st.getPrenom()+" "+st.getNom());
										alertG.setContentText(st.getScore()+" sélection(s), dernier tirage : tour n° "+st.getDernierTirage());
										alertG.showAndWait();
										lancer=false;
			});
					        return null;
					      }
					    };
					  }
					};
					lanceProgAlea.start();			
			}
		}
		});
		root.getChildren().add(nb);
		root.getChildren().add(tnb);
		Scene sc = new Scene(root);	
		fen.setScene(sc);

	}
	
	

	@SuppressWarnings("exports")
	public MenuBar getMenu(Stage fen)
	{
		//attention menu non clickable
		Menu menu = new Menu("");
		menu.setGraphic(new ImageView(getClass().getResource("menu-icon.png").toString()));
		MenuItem menuItem1 = new MenuItem("Liste des stagiaires");
		menuItem1.setOnAction(e -> {
			initSceneGestionStagiaire(fen);
			initFonct(fen);
			if (pr.afficheTous().isEmpty())
			{
				afficheNouveau();
			}
			else
			{
				affiche(pr.afficheTous().get(0));
				bprec.setDisable(true);
			}
		});
		MenuItem menuItem2 = new MenuItem("Programme Aleatoire");
		menuItem2.setOnAction(e -> {
		    initSceneProgAlea(fen);
		    
		});
		MenuItem menuItem3 = new MenuItem("A propos");
		menuItem3.setOnAction(e -> {
		    Label label =new Label("A propos");
		    label.setFont(Font.font ("Arial", 20));
		    GridPane root = new GridPane();
		    GridPane.setColumnIndex(label, 0);
				GridPane.setRowIndex(label, 1);
				GridPane.setHalignment(label, HPos.CENTER);
				 Label labelAuteur =new Label("Auteur : Aurélien MARTINEAU");
				 labelAuteur.setFont(Font.font ("Arial", 22));
				 GridPane.setColumnIndex(labelAuteur, 0);
					GridPane.setRowIndex(labelAuteur, 2);
					 GridPane.setColumnIndex(labelAuteur, 0);
						GridPane.setRowIndex(labelAuteur, 3);
				 Label labelLicence =new Label("Licence : LGPL");
				 labelLicence.setFont(Font.font ("Arial", 22));

		    root.getChildren().add(label);
		    root.getChildren().add(labelAuteur);
		    root.getChildren().add(getMenu(fen));
		    Scene scene = new Scene(root);
	        fen.setScene(scene);
		});
		menu.getItems().add(menuItem1);
		menu.getItems().add(menuItem2);
		menu.getItems().add(menuItem3);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menu);
		return menuBar;
	}
	@SuppressWarnings("exports")
	public void initSceneGestionStagiaire(Stage fen)
	{
		
		 lid = new Label ("Id :");
	        lnom = new Label ("Nom :");
	        lprenom = new Label ("Prénom :");
	        tid= new TextField("");
	        tid.setDisable(true);
	        tnom= new TextField("");
	        tprenom= new TextField("");
	            

	            
	        // pour les boutons
	        bsuiv = new Button("Suivant");
	        bprec = new Button("Précédent");
	        benreg = new Button("Enregistrer");
	        bsup = new Button("Suprimer");
	        bnouv = new Button("Nouveau");
	        
	        
	        // pour les éléments communs
	         GridPane.setColumnIndex(lid, 0);
	         GridPane.setRowIndex(lid, 0);
	         GridPane.setColumnIndex(tid, 1);
	         GridPane.setRowIndex(tid, 0);
	         
	         GridPane.setColumnIndex(lprenom, 0);
	         GridPane.setRowIndex(lprenom, 1);
	         GridPane.setColumnIndex(tprenom, 1);
	         GridPane.setRowIndex(tprenom, 1);
	         
	         GridPane.setColumnIndex(lnom, 0);
	         GridPane.setRowIndex(lnom, 2);
	         GridPane.setColumnIndex(tnom, 1);
	         GridPane.setRowIndex(tnom, 2);
	         
	             

	         
	         
	         
	         //pour les boutons
	         GridPane.setColumnIndex(bprec, 0);
	         GridPane.setRowIndex(bprec, 0);
	         GridPane.setColumnIndex(bnouv, 1);
	         GridPane.setRowIndex(bnouv, 0);
	         GridPane.setColumnIndex(benreg, 2);
	         GridPane.setRowIndex(benreg, 0);
	         GridPane.setColumnIndex(bsup, 3);
	         GridPane.setRowIndex(bsup, 0);
	         GridPane.setColumnIndex(bsuiv, 4);
	         GridPane.setRowIndex(bsuiv, 0);
	         

	        
	         GridPane root = new GridPane();
	         GridPane commun = new GridPane();
	         GridPane navigation = new GridPane();

	         commun.getChildren().addAll(lid,lprenom,lnom,tid,tprenom,tnom);
	         navigation.getChildren().addAll(bsuiv,bprec,benreg,bsup,bnouv);
	         SplitPane sp = new SplitPane();
	         sp.setOrientation(Orientation.VERTICAL);
	         sp.getItems().addAll(getMenu(fen),commun, navigation);
	         sp.setDividerPositions(0.8f, 0.2f);

	         root.getChildren().add(sp);
	        
	            Scene scene = new Scene (root);
	            fen.sizeToScene();
	            fen.setScene(scene);
	            if (pr.afficheTous().isEmpty())
	    		{
	            	bsuiv.setDisable(true);
					bprec.setDisable(true);
					benreg.setDisable(false);
					bnouv.setDisable(true);
					bsup.setDisable(true);
					nouveau=true;
	    		}
	            else
	            {
	            	nouveau=false;
	            	bsuiv.setDisable(false);
					bprec.setDisable(true);
					benreg.setDisable(false);
					bnouv.setDisable(false);
					bsup.setDisable(false);
					index=0;
					Stagiaire elementCourant=pr.affiche(0);
					affiche(elementCourant);
	            }
	}
	@SuppressWarnings("exports")
	public void affiche(Stagiaire st) {
		tid.setText(String.valueOf(st.getId()));
		tprenom.setText(st.getPrenom());
		tnom.setText(st.getNom());
	}
	public void afficheNouveau() {
		
		nouveau=true;
		Stagiaire st = new Stagiaire();
		affiche(st);
	}
	public void initFonct(Stage fen)
	{
		

	    @SuppressWarnings("unused")
		EventHandler<ActionEvent> arg0 = null;
		bsuiv.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						index++;
						nouveau=false;
						if(index>(pr.afficheTous().size()-1))
								{
							bsuiv.setDisable(true);
							bprec.setDisable(false);
							benreg.setDisable(false);
							bnouv.setDisable(false);
							bsup.setDisable(false);
							index--;
								}
						else
						{	
							bsuiv.setDisable(false);
							bprec.setDisable(false);
							benreg.setDisable(false);
							bnouv.setDisable(false);
							bsup.setDisable(false);
							Stagiaire elementCourant = pr.affiche(index);
							affiche(elementCourant);
							if (index==(pr.afficheTous().size()-1))
									{
										bsuiv.setDisable(true);
									}
						}
			
					}
			    	 
			     });
	    bprec.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						index--;
						nouveau=false;
						if(index<0)
								{
							bsuiv.setDisable(false);
							bprec.setDisable(true);
							benreg.setDisable(false);
							bnouv.setDisable(false);
							bsup.setDisable(false);
							index++;
								}
						else
						{	
							bsuiv.setDisable(false);
							bprec.setDisable(false);
							benreg.setDisable(false);
							bnouv.setDisable(false);
							bsup.setDisable(false);
							Stagiaire elementCourant = pr.affiche(index);
							affiche(elementCourant);
							if (index==0)
									{
										bprec.setDisable(true);
									}
						}
			
					}
			    	 
			     });
	    benreg.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Stagiaire element=new Stagiaire();
						try {
						element.setId(Integer.parseInt(tid.getText()));
						}
						catch(Exception e)
						{
							element.setId(0);
						}
						element.setPrenom(tprenom.getText());
						element.setNom(tnom.getText());
						String mot="";
					try {
						if (nouveau==true)
						{
							int nb=pr.ajoute(element);
									//mger.addElementChimique(element);
							tid.setId(String.valueOf(nb));
							bsuiv.setDisable(false);
							bprec.setDisable(false);
							benreg.setDisable(false);
							bnouv.setDisable(false);
							bsup.setDisable(false);
							nouveau=false;
							mot="Insertion";
							tid.setText(String.valueOf(nb));
							//bsuiv.fireEvent(arg0);
							
						}
						else
						{
						pr.modifieStagiaire(element);
						mot="Modification";
						}
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Enregistrement");
						alert.setHeaderText(mot+ " effectuée");
						alert.setContentText(mot+" du stagiaire "+element.getPrenom()+" "+element.getNom());
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.initOwner(fen);
						alert.showAndWait();
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	Dialog.
					}
				    });
	    bsup.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						
					try {
						Stagiaire candidat = pr.affiche(index);
						pr.suppr(candidat);
						index--;
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Enregistrement");
						alert.setHeaderText("Suppression effectuée");
						alert.setContentText("Suppression du stagiaire "+candidat.getPrenom()+" "+candidat.getNom());
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.initOwner(fen);
						alert.showAndWait();
					if(pr.afficheTous().isEmpty())
					{
						afficheNouveau();
						bsup.setDisable(false);
						bsuiv.setDisable(false);
						bprec.setDisable(false);
						benreg.setDisable(true);
						bnouv.setDisable(false);
					}
					else
					{
						if (index<0)
						{
							index=0;
						}
						Stagiaire elementCourant = pr.affiche(index);
						//ElementChimique elementCourant = mger.getElementChimique(index);
						affiche(elementCourant);
					}
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					}
				    });
	    bnouv.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						afficheNouveau();
						bsup.setDisable(false);
						//index=pr.afficheTous().size();
					}
				    });
	}
    public static void main(String[] args) {
        launch();
    }
    

}