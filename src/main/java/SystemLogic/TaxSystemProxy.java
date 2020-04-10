package SystemLogic;

public class TaxSystemProxy implements IExternalSystem{
    TaxSystem taxSystem;

    @Override
    public boolean connectToSystem() {
        return taxSystem.connectToSystem();
    }

    @Override
    public void SetInformation() {

    }

    @Override
    public void getInformation() {

    }
}
