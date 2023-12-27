package clients.reservation;


public class reservationController {
    private reservationModel model = null;
    private reservationView  view  = null;
  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public reservationController( reservationModel model, reservationView view )
  {
    this.view  = view;
    this.model = model;

    view.setController(this);

  }

  public void loadTable(){
    model.retrieveTableData();
  }



}
